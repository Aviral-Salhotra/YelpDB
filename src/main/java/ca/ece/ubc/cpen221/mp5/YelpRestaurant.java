package ca.ece.ubc.cpen221.mp5;

import java.util.HashSet;

public class YelpRestaurant extends Business {

	private int review_count;
	private String photo_url;
	private String type;
	private String url;
	private HashSet<String> neighborhoods;
	private int price;
	private HashSet<String> schools;
	private Double stars;

	private HashSet<String> categories;

	public YelpRestaurant(String business_id) {
		super(business_id);
	}

	public int getReview_count() {
		return review_count;
	}

	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HashSet<String> getNeighborhoods() {
		return neighborhoods;
	}

	public void setNeighborhoods(HashSet<String> neighborhoods) {
		this.neighborhoods = neighborhoods;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public HashSet<String> getSchools() {
		return schools;
	}

	public void setSchools(HashSet<String> schools) {
		this.schools = schools;
	}

	public Double getStars() {
		return stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

	public HashSet<String> getCategories() {
		return categories;
	}

	public void setCategories(HashSet<String> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "[open = " + super.getOpen() + ", url = " + url + ", longitude = " + super.getLongitude() + ", "
				+ "neighborhoods = " + neighborhoods + ", business_id = " + super.getBusiness_id() + "," + " name = "
				+ super.getName() + ", categories = " + categories + ", state = " + super.getState() + "," + " type = "
				+ type + ", stars = " + stars + ", city = " + super.getCity() + ", " + "full_address = "
				+ super.getFull_address() + ", review_count = " + review_count + ", " + "photo_url = " + photo_url
				+ ", schools = " + schools + ", " + "latitude = " + super.getLatitude() + ", price = " + price + "]";
	}

}
