package ca.ubc.ece.cpen221.mp5.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpRestaurant;
import ca.ece.ubc.cpen221.mp5.YelpReview;
import ca.ece.ubc.cpen221.mp5.YelpUser;

public class YelpClassesTests {

	@Test
	public void testRestaurant() {
		YelpRestaurant yr = new YelpRestaurant("id");
		yr.setReview_count(3);
		yr.setUrl("url");
		yr.setState("state");
		yr.setPhoto_url("url");
		yr.setCity("van");
		yr.setLatitude(3.0);
		yr.setLongitude(3.0);
		yr.setStars(3.0);
		yr.setPrice(4);
		yr.setFull_address("asdf");
		HashSet<String> categories = new HashSet<String>();
		yr.setCategories(categories);
		yr.setName("Test");
		yr.setOpen(true);
		yr.setNeighborhoods(categories);

		yr.setType("greek");
		yr.setSchools(categories);
		
		String yrStr = yr.toString();
		assertTrue(yrStr.contains("url"));
		
		assertEquals("state", yr.getState());
		assertEquals("url", yr.getUrl());
		assertEquals("id", yr.getBusiness_id());
		assertEquals("asdf", yr.getFull_address());
		assertEquals("van", yr.getCity());
		assertEquals(3, yr.getReview_count());
		assertEquals("url", yr.getPhoto_url());
		assertEquals(3.0, yr.getLatitude(), 10e-9);
		assertEquals(3.0, yr.getLongitude(), 10e-9);
		assertEquals(3.0, yr.getStars(), 10e-9);
		assertEquals(4, yr.getPrice());
		assertEquals(new HashSet<String>(), yr.getCategories());
		assertEquals("Test", yr.getName());
		assertTrue(yr.getOpen());
		assertEquals(new HashSet<String>(), yr.getNeighborhoods());

		assertEquals("greek", yr.getType());
		assertEquals(new HashSet<String>(), yr.getSchools());

	}
	
	@Test
	public void testYelpUser() {
		YelpUser u = new YelpUser("id");
		u.setReview_count(3);
		u.setName("u");
		u.setUrl("url");
		u.setType("type");
		u.setVotes(new HashMap<String,Integer>());
		u.setAverage_stars(2.0);
		
		assertEquals(3, u.getReview_count());
		assertEquals("u", u.getName());
		assertEquals("url", u.getUrl());
		assertEquals("type", u.getType());
		assertEquals(new HashMap<String, Integer>(), u.getVotes());
		assertEquals(2.0,u.getAverage_stars(),10e-9);
		assertEquals("id", u.getUser_id());
		//assertTrue(u.toString().contains("name"));
	}
	
	@Test
	public void testYelpReview() {
		YelpReview r = new YelpReview("review_id");
		r.setBusiness_id("id");
		r.setDate("2017-12-20");
		r.setReview_id("id");
		r.setText("5 stars");
		r.setType("type");
		r.setVotes(new HashMap<String, Integer>());
		r.setStars(3);
		r.setUser_id("id");
		assertEquals("id", r.getBusiness_id());
		assertEquals("id", r.getUser_id());
		assertEquals("id", r.getReview_id());
		assertEquals("2017-12-20", r.getDate());
		assertEquals("5 stars", r.getText());
		assertEquals("type", r.getType());
		assertEquals(new HashMap<String, Integer>(), r.getVotes());
		assertEquals(3, r.getStars());
		assertTrue(r.toString().contains("type"));
		
 		 
		
	}

}
