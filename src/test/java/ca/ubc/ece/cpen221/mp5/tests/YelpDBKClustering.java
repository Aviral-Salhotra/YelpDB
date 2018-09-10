package ca.ubc.ece.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.ClusterObject;
import ca.ece.ubc.cpen221.mp5.Point;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.YelpRestaurant;


public class YelpDBKClustering {

	@Test
	public void testkMeansClustering() throws FileNotFoundException {
		String pathRestaurants = "data/restaurants.json";
		String pathUsers = "data/users.json";
		String pathReviews = "data/reviews.json";
		
		YelpDB yelpDB= new YelpDB(pathRestaurants, pathUsers, pathReviews);
		String test = yelpDB.kMeansClusters_json(3);
		assertTrue(test.contains("cluster"));
		
	} 
	
	@Test
	public void testClusterObject(){
		YelpRestaurant test = new YelpRestaurant("bla");
		Point p1 = new Point(1,30,test); 
		Point p2 = new Point(1,31,test);
		Point p3 = new Point(1,30,test);
		
		assertEquals(p1,p3);
		assertEquals(p1,p1); 
		assertFalse(p1.equals(p2));
		assertFalse(p3.equals(test));
		
		String p2string = p2.toString();
		String p = "( " + 1.0 + ", " + 31.0 +")";
		assertEquals(p,p2string);
		
		ClusterObject clusterTest = new ClusterObject(p1,p2,1);
		
		
		assertEquals(5.0, clusterTest.getWeight(),10e-9);
		assertEquals(null,clusterTest.getName());
		assertEquals(1, clusterTest.getCluster().intValue());
		assertEquals(1.0, clusterTest.getX().doubleValue(), 10e-9);
		assertEquals(31.0,clusterTest.getY().doubleValue(),10e-9);
		
	  
	}
	
	
	

}
