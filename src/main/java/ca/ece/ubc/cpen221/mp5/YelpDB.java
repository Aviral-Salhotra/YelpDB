package ca.ece.ubc.cpen221.mp5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

import com.google.gson.Gson;


public class YelpDB implements MP5Db<YelpDB> {
	//Abstraction Function: Represents a database consisting of restaurants, users, and reviews
	//Rep-Invariant: No two restaurants share same id, no two users share same id, no two reviews share same id 
	//				Each Restaurant, User and Review is associated with a id 
			
	String restaurantsFile;
	String usersFile;
	String reviewsFile;
	HashMap<String, YelpRestaurant> restaurants = new HashMap<>();
	HashMap<String, YelpUser> users = new HashMap<>();
	HashMap<String, YelpReview> reviews = new HashMap<>();

	public YelpDB(String restaurantsFile, String usersFile, String reviewsFile) throws FileNotFoundException {
		//Abstraction Function: represents a Yelp database containing a map of users, restaurants, and reviews
		//Rep-Invariant: restaurants.contains(reviews.getRestaurantID)
		this.restaurantsFile = restaurantsFile;
		this.usersFile = usersFile;
		this.reviewsFile = reviewsFile;
 		Gson gson = new Gson();
		FileReader reader = new FileReader(restaurantsFile);
		Scanner scanner = new Scanner(reader);
		while (scanner.hasNextLine()) { 
			YelpRestaurant restaurantObject = gson.fromJson(scanner.nextLine(), YelpRestaurant.class);
			String business_id = restaurantObject.getBusiness_id();
			restaurants.put(business_id, restaurantObject);
		}
		scanner.close();

		FileReader userReader = new FileReader(usersFile);
		Scanner userScanner = new Scanner(userReader);
		while (userScanner.hasNextLine()) {
			YelpUser userObject = gson.fromJson(userScanner.nextLine(), YelpUser.class);
			String user_id = userObject.getUser_id();
			users.put(user_id, userObject);
		}
		userScanner.close();
		FileReader reviewReader = new FileReader(reviewsFile);
		Scanner reviewScanner = new Scanner(reviewReader);
		while (reviewScanner.hasNextLine()) {
			YelpReview reviewObject = gson.fromJson(reviewScanner.nextLine(), YelpReview.class);
			String review_id = reviewObject.getReview_id();
			reviews.put(review_id, reviewObject);
		}
		reviewScanner.close();

	}
	/**
	 * 
	 * @param s
	 * the json string representing the details of the new restaurant being added to the Yelp Database
	 * @return
	 * returns the newly made restaurant object
	 */
	
	public YelpRestaurant addRestaurant(String s) {
		Gson gson = new Gson();
		YelpRestaurant restaurantObject = gson.fromJson(s, YelpRestaurant.class);
		Integer BusinessID = (int) (Math.random() * 10);
		while(this.restaurants.containsKey(BusinessID.toString())){
			BusinessID = (int) (Math.random() * 10);
		}
		restaurantObject.setBusiness_id(BusinessID.toString());
		restaurantObject.setStars(0.0);
		String business_id = restaurantObject.getBusiness_id();
		restaurants.put(business_id, restaurantObject);
		return restaurantObject;
		
	}
	
	/**
	 * 
	 * @param s
	 * JSON string representing the name of the User
	 * @return
	 * the newly made YelpUser object
	 * @throws InvalidString
	 */
	
	public YelpUser addUser(String s) throws InvalidString {
		Gson gson = new Gson();
		YelpUser userObject = gson.fromJson(s, YelpUser.class);
		
		if(userObject.getName() == null) {
			throw new InvalidString();
		}
		 
		
		Integer UserID = (int) (Math.random() * 10);
		while(this.restaurants.containsKey(UserID.toString())){
			UserID = (int) (Math.random() * 10);
		}
		userObject.setUser_id(UserID.toString());
		userObject.setUrl("http://www.yelp.com/user_details?userid=" + UserID.toString());
		userObject.setAverage_stars(0.0);
		userObject.setReview_count(0);
		userObject.setType("user");
		HashMap<String,Integer> votes = new HashMap<String,Integer>();
		votes.put("funny", 0); votes.put("useful", 0); votes.put("cool",0);
		userObject.setVotes(votes);
		
		String user_id = userObject.getUser_id();
		users.put(user_id, userObject);
		return userObject;
	}
	
	/**
	 * 
	 * @param s
	 * A JSON string representing the details of the new review 
	 * @return
	 * The YelpReview object representing the newly created review
	 * @throws InvalidRestaurantID
	 * @throws InvalidUserID
	 */
	public YelpReview addReview(String s) throws InvalidRestaurantID, InvalidUserID{
		//RepInvariant: business id and user id associated with review must be valid, otherwise will throw 
		//				InvalidRestaurantID/InvalidUserID exceptions
		Gson gson = new Gson();
		YelpReview reviewObject = gson.fromJson(s, YelpReview.class);
		
		if(!this.restaurants.containsKey(reviewObject.getBusiness_id())) {
			throw new InvalidRestaurantID();
		}
		if(!this.users.containsKey(reviewObject.getUser_id())) {
			throw new InvalidUserID();
		}
		
		Integer ReviewID = (int) (Math.random() * 10);
		while(this.restaurants.containsKey(ReviewID.toString())){
			ReviewID = (int) (Math.random() * 10);
		}
		reviewObject.setReview_id(ReviewID.toString());
		reviewObject.setStars(0);
		reviewObject.setType("review");
		HashMap<String,Integer> votes = new HashMap<String,Integer>();
		votes.put("cool", 0); votes.put("funny", 0); votes.put("useful",0);
		reviewObject.setVotes(votes);
		
		
		String review_id = reviewObject.getReview_id();
		
		reviews.put(review_id, reviewObject);
		return reviewObject;
		
	}
	/**
	 * 
	 * @param business_id
	 * the business ID associated with a restaurant
	 * @return
	 * the YelpRestaurant object associated with the specified ID
	 * @throws InvalidRestaurantID 
	 */

	public YelpRestaurant getRestaurant(String business_id) throws InvalidRestaurantID {
		if(!this.restaurants.containsKey(business_id))
			throw new InvalidRestaurantID();

		return restaurants.get(business_id);
	}
	/**
	 * 
	 * @param user_id
	 * the user id associated with a specific Yelp User
	 * @return
	 * the YelpUser object associated with the specified user id
	 */

	public YelpUser getUser(String user_id) {

		return users.get(user_id);
	}

	/**
	 * 
	 * @param review_id
	 * the review id associated with a specific Yelp review
	 * @return
	 * the YelpReview object associated with the specified review id
	 */
	public YelpReview getReview(String review_id) {
		return reviews.get(review_id);
	}

	@Override
	public Set<YelpDB> getMatches(String queryString) {
		// TODO Auto-generated method stub
		Set<YelpDB> db = new HashSet<>();
		db.add(this);
		return db ;
	}
	/**
	 * @return String
	 * Returns a string representing a list of clusters in JSON format
	 * Each cluster is determined using K-means clustering and K-means plus plus initialization
	 * 
	 * 
	 */
	@Override
	public String kMeansClusters_json(int k) {
		// TODO Auto-generated method stub
		List<Point> dataset = this.restaurants.values().stream().map(r -> new Point(r)).collect(Collectors.toList());
		Kmeans kclusters = new Kmeans(dataset,k);
		boolean reattempt = true;
		List<Set<ClusterObject>> clusterList = new ArrayList<>();
		
		while(reattempt) {
			reattempt = false;
			clusterList = kclusters.clustering();
			//int count = 0;
			for(Set<ClusterObject> o : clusterList) {
				if(o.isEmpty())
					reattempt = true;
				
			}
		}
		
		
		
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(clusterList));
		return gson.toJson(clusterList);
	}

/**
 * 
 * @returns a list of points representing the locations of the restaurants in the database.
 */
	public ArrayList<Point> getPoints() {
		ArrayList<Point> points = new ArrayList<Point>();

		for (String s : this.restaurants.keySet()) {
			YelpRestaurant tempRestaurant = this.restaurants.get(s);
			double longitude = tempRestaurant.getLongitude();
			double latitude = tempRestaurant.getLatitude();
			Point point = new Point(longitude, latitude, tempRestaurant);
			points.add(point);
		}

		return points;
	}
/**
 * Gets a predictor function for a given user that predicts user rating for a restaurant based on the price factor 
 * of the restaurant
 * @param String user_id
 * requires that user has at least one review for a given restaurant 
 * @returns a binary function which can be used to predict the user's ratings for other restaurants based on price
 */
	@Override
	public ToDoubleBiFunction<MP5Db<YelpDB>, String> getPredictorFunction(String user) {
		//abstraction function
		List <Integer> x = this.reviews.keySet().stream()
				 .map( r->this.reviews.get(r))
				 .filter(r -> r.getUser_id().equals(user))
				 .map(r-> this.restaurants.get(r.getBusiness_id()).getPrice())
				 .collect(Collectors.toList());
		List <Integer> y = this.reviews.keySet().stream()
				 .map( r->this.reviews.get(r))
				 .filter(r -> r.getUser_id().equals(user))
				 .map(r->r.getStars())
				 .collect(Collectors.toList());
		System.out.println(x);
		System.out.println(y);
		 ToDoubleBiFunction<MP5Db<YelpDB>, String> userPredictor = new LinearRegression(x,y);
		 System.out.println(userPredictor);
		 return userPredictor; 
		 
	} 

	

}
