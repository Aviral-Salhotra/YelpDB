package ca.ece.ubc.cpen221.mp5;
/*
 * A class to represent a generic business
 */
public class Business {
	//Abstraction function: Represents an established business with a location 
	//Rep-Invariant: Business_id must be unique 
	private String business_id;
	private String name;
	private String full_address;
	private String city;
	private String state;
	private double longitude;
	private double latitude;
	private boolean open;

	public Business(String business_id) {
		this.business_id = business_id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_address() {
		return full_address;
	}

	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
