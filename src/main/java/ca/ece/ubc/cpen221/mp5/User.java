package ca.ece.ubc.cpen221.mp5;

/*
 * A class that represents a generic user 
 */
public class User {
	// Abstraction Function: Represents an online user 
	// Rep-Invariant: user_id must be unique 
	private String name;
	private String user_id;

	public User(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
