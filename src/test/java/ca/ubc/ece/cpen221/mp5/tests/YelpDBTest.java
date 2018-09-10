package ca.ubc.ece.cpen221.mp5.tests;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.ClusterObject;
import ca.ece.ubc.cpen221.mp5.InvalidRestaurantID;
import ca.ece.ubc.cpen221.mp5.Kmeans;
import ca.ece.ubc.cpen221.mp5.LinearRegression;
import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.Point;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.YelpRestaurant;
import ca.ece.ubc.cpen221.mp5.YelpReview;
import ca.ece.ubc.cpen221.mp5.YelpUser;

public class YelpDBTest {

	

	@Test
	public void testParser() throws FileNotFoundException, InvalidRestaurantID {
		String pathRestaurants = "data/restaurants.json";
		String pathUsers = "data/users.json";
		String pathReviews = "data/reviews.json";
		
		YelpDB yelpDB= new YelpDB(pathRestaurants, pathUsers, pathReviews);
		yelpDB.kMeansClusters_json(3);
		YelpRestaurant jasmine = yelpDB.getRestaurant("BJKIoQa5N2T_oDlLVf467Q"); 
		assertEquals("Berkeley",jasmine.getCity());
		assertEquals(true,jasmine.getOpen());
		assertEquals("http://www.yelp.com/biz/jasmine-thai-berkeley",jasmine.getUrl());
		assertEquals("Berkeley",jasmine.getCity());	
		YelpUser Elise = yelpDB.getUser("zkjy_XoVgR2EFjLjtzFDNw");
		assertEquals("Elise B.", Elise.getName());
		assertEquals(7, Elise.getReview_count());
		int cool = Elise.getVotes().get("cool");
		assertEquals(4, cool);
		YelpReview laVals = yelpDB.getReview("2RBIHXTYyrOZLmZQ3ZIh_g");
		assertEquals(4, laVals.getStars());
		assertEquals("1CBs84C-a-cuA3vncXVSAw", laVals.getBusiness_id());
		System.out.println("LaVal's lover: " + yelpDB.getReview("2RBIHXTYyrOZLmZQ3ZIh_g").getUser_id() );
		System.out.print((yelpDB.getRestaurant("gclB3ED6uk6viWlolSb_uA").getUrl()));
	} 
 @Test 
 public void testPoint() {
	 YelpRestaurant test = new YelpRestaurant("bla");
	 Point p1 = new Point(1,3,test); 
	 Point p2 = new Point(1,5,test);
	 Point p3 = new Point(4,7,test); 
	 assertEquals(2,p1.getDistance(p2),10e-9);
	 assertEquals(5,p1.getDistance(p3),10e-9);
 }
 @Test
 public void testkplus() throws FileNotFoundException {

	 YelpRestaurant test = new YelpRestaurant("bla");
	 YelpRestaurant test2 = new YelpRestaurant("dev");
	 YelpRestaurant test3 = new YelpRestaurant("avy");
	 Point p1 = new Point(1,30,test); 
	 Point p2 = new Point(1,31,test);
	 Point p3 = new Point(1,32,test);
	 Point p4 = new Point(1,35,test2);
	 Point p5 = new Point(1,38,test);
	 Point p6 = new Point(1,27,test);
	 Point p7 = new Point(1,29,test);
	 Point p8 = new Point(1,33,test);
	 Point p9 = new Point(1,36,test3);
	 
	 Point p10 = new Point(1,1,test);
	 Point p11= new Point(1,0,test2);
	 Point p12= new Point(1,2,test);
	 
	 Point p13 = new Point(9,100,test);
	 Point p14 = new Point(10,100,test);
	 
	 
	 ArrayList<Point> dataset = new ArrayList(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14));
	 Kmeans test1 = new Kmeans(dataset,3);
	
	 
	 List<Set<ClusterObject>> temp = test1.clustering();
	 
 } 

 @Test 
 public void RegressionTest() {
	 ArrayList <Integer> xData = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
	 ArrayList <Integer> yData = new ArrayList<Integer>(Arrays.asList(3,5,7,9));
	 LinearRegression f_2x = new LinearRegression(xData,yData);
	 assertEquals(1,f_2x.getRSquared(),10e-9);
	 assertEquals(17.0,f_2x.applyAsDouble(7),10e-9);
 } 

 
 @Test
 public void test() throws FileNotFoundException {
	 YelpDB database = new YelpDB("data/restaurants.json", "data/users.json","data/reviews.json");
	 String input = "{\"open\": true, \"url\": \"http://www.yelp.com/biz/la-cascada-taqueria-berkeley\", \"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\r\n";

	 YelpRestaurant res = database.addRestaurant(input);
	 System.out.println(res.toString());
 }

	@Test
	public void RegressionYelpTest() throws FileNotFoundException {
		String pathRestaurants = "data/restaurants.json";
		String pathUsers = "data/users.json";
		String pathReviews = "data/reviews.json";
		
		YelpDB yelpDB= new YelpDB(pathRestaurants, pathUsers, pathReviews); 
		try {
		ToDoubleBiFunction<MP5Db<YelpDB>, String> r = yelpDB.getPredictorFunction("90wm_01FAIqhcgV_mPON9Q");
		double prediction = r.applyAsDouble(yelpDB, "gclB3ED6uk6viWlolSb_uA"); 
		}
		catch (ArithmeticException e) {	
		}
		ToDoubleBiFunction<MP5Db<YelpDB>, String> r = yelpDB.getPredictorFunction("wr3JF-LruJ9LBwQTuw7aUg");
		double prediction = r.applyAsDouble(yelpDB, "gclB3ED6uk6viWlolSb_uA"); 
		
		ToDoubleBiFunction<MP5Db<YelpDB>, String> r2 = yelpDB.getPredictorFunction("FGmwQ2eV2pOy1j7u4Q-XrQ");
		double p1 = r2.applyAsDouble(yelpDB, "gclB3ED6uk6viWlolSb_uA"); 
		System.out.println(prediction);
	} 

}          


