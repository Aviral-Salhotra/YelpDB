package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;



public class YelpClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public YelpClient(String hostname, int port) throws IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public void sendRequest(String x) throws IOException {
		out.print(x + "\n");
		out.flush(); // important! make sure x actually gets sent
	}

	public String getReply() throws IOException {
		String reply = in.readLine();
		if (reply == null) {
			throw new IOException("connection terminated unexpectedly");
		}

		try {
			return new String(reply);
		} catch (NullPointerException e) {
			throw new IOException("misformatted reply: " + reply);
		}
	}
	
	 public void close() throws IOException {
	        in.close();
	        out.close();
	        socket.close();
	    }
	 
	 //private final static String N = "GETRESTAURANT h_we4E3zofRTf4G0JTEF0A";
	//private final static String N = "ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/la-cascada-taqueria-berkeley\", \"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\r\n";

	//private final static String N  = "ADDRESTAURANT {\"open\": true, \"url\": \"http://www.yelp.com/biz/la-cascada-taqueria-berkeley\", \"longitude\": -122.2663047, \"neighborhoods\": [\"Downtown Berkeley\", \"UC Campus Area\"], \"name\": \"La Cascada Taqueria\", \"categories\": [\"Mexican\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.0, \"city\": \"Berkeley\", \"full_address\": \"2164 Center St\\nDowntown Berkeley\\nBerkeley, CA 94704\", \"review_count\": 101, \"photo_url\": \"http://s3-media3.ak.yelpcdn.com/bphoto/-Nwor_L01Lsm6Mcf36-QCA/ms.jpg\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 37.8705337, \"price\": 2}\r\n";
	private final static String N  = "ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAwAA\", \"votes\": {\"cool\": 0, \"useful\": 0, \"funny\": 0}, \"text\": \"I think they have the best pizza in Berkeley!  But get there before lunch because the line goes out the door. The price is unbeatable, $2.50 for a slice and a soda and their thin crust it deeelish!!  mmmmm...  the margharita slice is my favorite! They put tons of garlic!\", \"stars\": 4, \"user_id\": \"ej9j-Vof_xAq9cZQ5WxRbgAA\", \"date\": \"2006-11-30\"}"; 
	 		
	 
	 public static void main(String[] args) {
	        try {
	            YelpClient client = new YelpClient("localhost", YelpDBServer.SERVER_PORT);
	            
	            client.sendRequest(N);
	                       
	            String y = client.getReply();
	            System.out.println("reply" + y);

	           // client.close();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	    }

}
