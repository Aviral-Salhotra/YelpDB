package ca.ece.ubc.cpen221.mp5;


/*
 * A class that represents a generic review
 */
public class Review {
	//Abstraction Function: Represents an online review with text, date, user_id etc
	//Rep Invariant: Only one value associated with each attribute
	//review_id, text, user_id, date may not be null.
	private String review_id;
	private String text;
	private String user_id;
	private String date;
	public Review(String review_id) {
		this.review_id = review_id;
	}

	public String getReview_id() {
		return review_id;
	}

	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date =date;
		
	}
	public String getText() {
		return text;
	}
 
	public void setText(String text) {
		this.text = text;
	}
	

	
}
