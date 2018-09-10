package ca.ece.ubc.cpen221.mp5;

public class ClusterObject {

	private Double x;

	private Double y;

	private String name;

	private Integer cluster;
	private Double weight;
/**
 * 
 * @param p
 * A point representing the coordinate (longitude and latitude) of a restaurant
 * 
 * @param centroid
 * The coordinate point corresponding to the centroid associated to the cluster the 
 * ClusterObject is a part of
 * 
 * @param cluster
 * The integer value representing the identification value of the cluster the ClusterObject
 * is a part of from a list of clusters
 */
	public ClusterObject(Point p, Point centroid, Integer cluster) {
		//Abstraction Function: represents a point that is a part of a cluster
		//Rep Invariant: Each clusterObject marks a longitude and latitude corresponding 
		//				 to a Restaurant in the database
		this.x = centroid.getLatitude();
		this.y = centroid.getLongitude();
		this.name = p.getRestaurant().getName();
		this.cluster = cluster;
		this.weight = 5.0;
	}

	public Double getWeight() {
		return weight;
	}

	public String getName() {
		return name;
	}

	public Integer getCluster() {
		return cluster;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

}
