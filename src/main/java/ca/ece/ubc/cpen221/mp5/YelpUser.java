package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;

public class YelpUser extends User
{
  
	private int review_count;
    private HashMap<String,Integer> votes;
    private String type;
    private String url;
    private double average_stars;
    
    public YelpUser(String user_id) {
  		super(user_id);
  	}
    
    public int getReview_count ()
    {
        return review_count;
    }

    public void setReview_count (int review_count)
    {
        this.review_count = review_count;
    }

    public HashMap<String,Integer> getVotes ()
    {
        return votes;
    }

    public void setVotes (HashMap<String,Integer> votes)
    {
        this.votes = votes;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public double getAverage_stars ()
    {
        return average_stars;
    }

    public void setAverage_stars (double average_stars)
    {
        this.average_stars = average_stars;
    }

    @Override
    public String toString()
    {
        return " [url = "+url+", votes = "+votes+", review_count = "+review_count+", type = "+type+", user_id = "+super.getUser_id()+", name = "+super.getName()+", average_stars = "+average_stars+"]";
    }
}
			