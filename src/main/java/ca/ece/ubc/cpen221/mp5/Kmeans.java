package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Kmeans {
	private List<Point> dataset;
	private int numCluster;
	
	/**
	 * 
	 * @param dataset
	 * A list of points representing the dataset for which clustering will occur
	 * @param numCluster
	 * An integer representing the desired number of clusters the dataset should
	 * be assigned to
	 */

	public Kmeans(List<Point> dataset, int numCluster) {
		//Abstraction Function: a group of points that need to be clustered in 
		//						association with a numerical value representing 
		//						the number of desired clusters
		//Rep-Invariant: !dataset.isEmpty
		this.dataset = dataset;
		this.numCluster = numCluster;
	}

	/**
	 * 
	 * @return HashSet<Point>
	 * returns the initial centroid chosen using the K-means plus plus
	 * initialization method
	 * 
	 */
	public HashSet<Point> getInitialCentroids() {
		Random rand = new Random();
		HashMap<Point, Double> probDistribution = new HashMap<Point, Double>();
		HashSet<Point> centroids = new HashSet<Point>();

		int centerIndex = rand.nextInt(this.dataset.size() - 1);
		Point center = new Point(this.dataset.get(centerIndex).getLatitude(),
				this.dataset.get(centerIndex).getLongitude(), this.dataset.get(centerIndex).getRestaurant());
		centroids.add(center);
		while (centroids.size() < this.numCluster) {
			double sum = 0.0;
			for (Point p : this.dataset) {
				double nearestCentroid = centroids.stream().map(c -> Math.pow(c.getDistance(p), 2))
						.reduce(Double.MAX_VALUE, Double::min);
				probDistribution.put(p, nearestCentroid);
				sum += nearestCentroid;
			}
			final double sumF = sum;
			probDistribution.forEach((p, v) -> probDistribution.put(p, v / sumF));
			centroids.add(pickCentroid(probDistribution));
		}
		return centroids;
	}

	/**
	 * 
	 * @param probabilityDistribution
	 * a map representing the probability distribution for a set of points
	 * probability values are dependent on the location of a point in comparison
	 * to the chosen centroids
	 * @return Point
	 * returns a point chosen from the probability distribution map in 
	 * accordance with a probability distribution
	 * 
	 */
	public Point pickCentroid(HashMap<Point, Double> probabilityDistribution) {
		double random = Math.random();
		double cumulativeProbability = 0.0;
		for (Point p : probabilityDistribution.keySet()) {
			double probability = probabilityDistribution.get(p);
			cumulativeProbability += probability;
			if (random <= cumulativeProbability) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return List<Set<ClusterObject>>
	 * returns a list of sets of ClusterObjects. Each set represents a cluster 
	 * determined using the kmeans clustering method
	 */

	public List<Set<ClusterObject>> clustering() {
		HashMap<Point, Set<Point>> clusters = new HashMap<Point, Set<Point>>();
		HashSet<Point> centroids = this.getInitialCentroids();
		HashSet<Point> centroidsPrev = new HashSet<Point>();

		while (!this.checkMatch(centroidsPrev, centroids)) {
			clusters.clear();
			for (Point c : centroids)
				clusters.put(c, new HashSet<Point>());

			centroidsPrev = centroids;

			for (Point p : this.dataset) {
				Point closest = Collections.min(centroids, Comparator.comparingDouble(c -> c.getDistance(p)));
				Set<Point> set = clusters.get(closest);
				set.add(p);
				clusters.put(closest, set);
			}

			centroids = this.getCentroids(clusters);

		}


		List<Set<ClusterObject>> clusterList = new ArrayList<Set<ClusterObject>>();

		Integer clusterNum = 0;
		for (Point centroid : clusters.keySet()) {
			clusterNum++;
			Integer n = clusterNum;
			Set<Point> group = clusters.get(centroid);

			clusterList.add(group.stream().map(p -> new ClusterObject(p, centroid, n)).collect(Collectors.toSet()));

		}

		return clusterList;

	}
	/**
	 * 
	 * @param clusters
	 * sets of points for which centroids must be found
	 * @return HashSet<Point>
	 * a hashset of points representing the newly computed cluster centroids (measured 
	 * by taking the means of longitudes and latitudes of the cluster points)
	 */

	public HashSet<Point> getCentroids(HashMap<Point, Set<Point>> clusters) {
		HashSet<Point> newCentroids = new HashSet<Point>();

		for (Point c : clusters.keySet()) {
			List<Double> longitudes = new ArrayList<Double>();
			List<Double> latitudes = new ArrayList<Double>();
			clusters.get(c).stream().mapToDouble(x -> x.getLongitude()).forEach(longitudes::add);
			clusters.get(c).stream().mapToDouble(x -> x.getLatitude()).forEach(latitudes::add);
			Double averageLong = longitudes.stream().mapToDouble(val -> val).average().getAsDouble();
			Double averageLat = latitudes.stream().mapToDouble(val -> val).average().getAsDouble();
			newCentroids.add(new Point(averageLat, averageLong));
		}

		return newCentroids;

	}
	
	/**
	 * 
	 * @param prevPoints
	 * @param currentPoints
	 * @return boolean
	 * checks if prevPoints contains equal Point objects as currentPoints (latitudes
	 * and longitudes are equivalent between points)
	 */
	private boolean checkMatch(HashSet<Point> prevPoints, HashSet<Point> currentPoints) {

		HashSet<Double> xPrev = new HashSet<Double>();
		HashSet<Double> yPrev = new HashSet<Double>();
		HashSet<Double> xCurr = new HashSet<Double>();
		HashSet<Double> yCurr = new HashSet<Double>();
		prevPoints.stream().mapToDouble(a -> a.getLatitude()).forEach(xPrev::add);
		prevPoints.stream().mapToDouble(a -> a.getLongitude()).forEach(yPrev::add);
		currentPoints.stream().mapToDouble(a -> a.getLatitude()).forEach(xCurr::add);
		currentPoints.stream().mapToDouble(a -> a.getLongitude()).forEach(yCurr::add);

		if (xPrev.equals(xCurr) && yPrev.equals(yCurr))
			return true;

		return false;
	}

}