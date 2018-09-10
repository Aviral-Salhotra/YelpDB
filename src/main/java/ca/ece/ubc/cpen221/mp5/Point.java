package ca.ece.ubc.cpen221.mp5;



public class Point {
	private double longitude;
	private double latitude;
	private YelpRestaurant restaurant;
	
	/**
	 * 
	 * @param latitude
	 * the latitude associated with the coordinate point
	 * @param longitude
	 * the longitude associated with the coordinate point
	 * @param restaurant
	 * the restaurant associated with the coordinate
	 */
	public Point(double latitude, double longitude, YelpRestaurant restaurant) {
		//Abstraction Function: represents a coordinate point with a numerical latitude,
		//						longitude, and corresponding restaurant
		//Rep-Invariant: YelpDB.contains(restaurant)
		this.longitude = longitude;
		this.latitude = latitude;
		this.restaurant =restaurant;
	}
	public Point(double latitude, double longitude) {
		//Abstraction Function: represents a coordinate point with a numerical latitude,
		//						longitude, without a corresponding restaurant
		this.longitude = longitude;
		this.latitude = latitude;
		this.restaurant = null;
	}
	/**
	 * 
	 * @param restaurant
	 * the restaurant for which a coordinate is being documented
	 */
	public Point(YelpRestaurant restaurant) {
		this.longitude = restaurant.getLongitude();
		this.latitude = restaurant.getLatitude();
		this.restaurant = restaurant;
	}
	
	/**
	 * 
	 * @return YelpRestaurant
	 * returns the restaurant associated with the coordinate
	 * returns null if the coordinate does not have an associated restaurant
	 */
	public YelpRestaurant getRestaurant() {
		return this.restaurant;
	}
	
	/**
	 * 
	 * @return
	 * returns the longitude value associated with the point
	 */
	public double getLongitude() {
		return this.longitude;
	}
	/**
	 * 
	 * @return
	 * returns the latitude value associated with the point
	 */
	public double getLatitude() {
		return this.latitude;
	}
	/**
	 * 
	 * @param p
	 * the reference Point from which the distance will be computed
	 * @return
	 * double value representing the distance between this.Point and 
	 * the passed point p
	 */
	public double getDistance(Point p) {
		double distance;
		distance = Math.pow(this.longitude-p.longitude, 2.0) + Math.pow(this.latitude - p.latitude, 2.0);
		distance = Math.sqrt(distance);
		return distance;
	}
	
	/**
	 * @return
	 * the String representing this coordinate in the form "(x,y)"
	 */
	@Override
	public String toString() {
		String p = "( " + this.latitude + ", " + this.longitude +")";
		return p;
	}
	
	/**
	 * @return
	 * returns true if two coordinates are equal to one another, meaning they have
	 * the same latitude and longitude values. 
	 * returns false otherwise. 
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		if(this.getLongitude() == other.getLongitude() && this.getLatitude() == other.getLatitude())
			return true;
		else
			return false;
	}

}
