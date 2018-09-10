package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;

public class YelpReview extends Review {
	private int stars;
	private HashMap<String, Integer> votes;
	private String business_id;
	private String type;

	public YelpReview(String review_id) {
		super(review_id);
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public HashMap<String, Integer> getVotes() {
		return votes;
	}

	public void setVotes(HashMap<String, Integer> votes) {
		this.votes = votes;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return " [type = " + type + ", business_id = " + business_id + ", votes = " + votes + ", review_id = "
				+ super.getReview_id() + ", text = " + super.getText() + ", stars = " + stars + ", user_id = "
				+ super.getUser_id() + ", date = " + super.getDate() + "]";
	}
 
}
 
