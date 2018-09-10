package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {
	public static final int SERVER_PORT = 4949; 
	private ServerSocket serverSocket;
	private YelpDB database;

	public YelpDBServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		this.database = new YelpDB("data/restaurants.json", "data/users.json", "data/reviews.json");
	}

	public void serve() throws IOException {
		while (true) {
			// block until a client connects
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						// this exception wouldn't terminate serve(),
						// since we're now on a different thread, but
						// we still need to handle it
						ioe.printStackTrace();
					}
				}
			});
			// start the thread
			handler.start();
		}
	}

	/**
	 * @param socket
	 * @throws IOException
	 */
	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");

		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			
			String line = in.readLine();

			System.err.println("request: " + line);
			try {
				String[] input = line.split("\\s+", 2);

				
				if (line.contains("GETRESTAURANT")) {
					
					YelpRestaurant res;
					try {
						res = getRes(input[1]);
						System.err.println("reply: " + res.toString());
						out.println(res.toString());
					} catch (InvalidRestaurantID e) {
						System.err.println("ERR: INVALID ID");
					}
					
				}
				else if (line.contains("ADDRESTAURANT")) {
					YelpRestaurant res = this.addRes(input[1]);

					System.err.println("reply: " + res.toString());
					out.println(res.toString());

				}
				else if (line.contains("ADDUSER")) {
					YelpUser user;
					try {
						user = this.addUser(input[1]);
						System.err.println("reply: " + user.toString());
					} catch (InvalidString e) {
						System.err.println("ERR: INVALID_USER_STRING");
					}
					
				}
				else if (line.contains("ADDREVIEW")) {
					try {
						YelpReview review= this.addReview(input[1]);
						System.err.println("reply: " + review.toString());
					}catch(InvalidRestaurantID e) {
						System.err.println("ERR: NO_SUCH_RESTAURANT");
					}catch(InvalidUserID e) {
						System.err.println("ERR: NO_SUCH_USER");
					}
					
					
				}
				else {
					System.err.println("ERR: ILLEGAL REQUEST");
				}

			} catch (NullPointerException e) {
				// complain about ill-formatted request
				System.err.println("reply: err");
				out.print("err\n");
			}
			// important! our PrintWriter is auto-flushing, but if it were
			// not:
			// out.flush();

		} finally {
			out.close();
			in.close();
		}
	}

	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(SERVER_PORT);
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * 
 * @param id
 * The business id referring to a restaurant stored in the database
 * @return
 * The restaurant object represented by this id
 * @throws InvalidRestaurantID 
 */
	private YelpRestaurant getRes(String id) throws InvalidRestaurantID {
		YelpRestaurant res = this.database.getRestaurant(id);
		return res;
	}

	private YelpRestaurant addRes(String input) {
		YelpRestaurant res = database.addRestaurant(input);
		return res;
	}
	
	private YelpUser addUser(String input) throws InvalidString {
		YelpUser user = database.addUser(input);
		return user;
	}
	
	private YelpReview addReview (String input) throws InvalidRestaurantID, InvalidUserID {
		
			YelpReview review = database.addReview(input);
			return review;
		
	
	}

}
