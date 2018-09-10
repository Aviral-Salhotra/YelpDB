package ca.ece.ubc.cpen221.mp5;
import java.util.List;
import java.util.function.ToDoubleBiFunction;


public class LinearRegression implements ToDoubleBiFunction<MP5Db<YelpDB>, String> {

	private double a;
	private double b;
	private double r_squared;
	/**
	 * 
	 * @param a,b 
	 * @param r_squared
	 * Constructs a new linear regression with equation, y = a*x + b
	 * r_squared represents the stastical value of the strength of this regression
	 */
	public LinearRegression(double a, double b, double r_squared) {
		this.a = a;
		this.b = b;
		this.r_squared = r_squared;
	}
	/**
	 * @param List of Integers x
	 * @param List of Integers y
	 * 	requires The lists are such that x[n] maps to the value y[n] for 0<=n<= size of x
	 * Analyzes the two lists to create a new linear regression representing a linear function from x to y.
	 * @throws ArithmeticException
	 */
	public LinearRegression(List<Integer> x, List<Integer> y) {
		double x_mean = x.stream().mapToInt(xval->xval).average().getAsDouble();
		double y_mean = y.stream().mapToInt(yval->yval).average().getAsDouble();
		
		double Sxx = x.stream().mapToDouble(xdouble-> Math.pow(xdouble-x_mean,2)).sum();
		double Syy = y.stream().mapToDouble(ydouble-> Math.pow(ydouble-y_mean,2)).sum();
		double Sxy=0.0;
		if (Sxx ==0 || Syy==0) {
			throw new ArithmeticException();
		}
 		System.out.println(Sxx);
		System.out.println(Syy);
		
 		
		for(int i=0;i<x.size();i++) {
			Sxy += (x.get(i) - x_mean)*(y.get(i)-y_mean);
		}
		this.b = Sxy/Sxx; 
		this.a = y_mean - b*x_mean;
		this.r_squared =  Math.pow(Sxy, 2) /(Sxx*Syy);
		
		
		}
/**
 * @param x
 * @return double representing the predicted value of y for given x, based on regression 
 */
	public double applyAsDouble (int x) {
		// TODO Auto-generated method stub
		return b*x+a;
	}
/**
 * @return the value for getRSquared()
 */
	public double getRSquared() {
		return r_squared;
	}
/**
 * Creates a string representation of ax+b=y
 */
	@Override 
	public String toString() {
		return this.b + "x" + " + " + this.a;
	}
	
/**
 * Returns the predicted rating from a user based on the price of a restaurant
 */
	@Override
	public double applyAsDouble(MP5Db<YelpDB> t, String u) {
		// TODO Auto-generated method stub
		YelpDB db = t.getMatches(u).iterator().next();
		int x = 0;
		try {
			x = db.getRestaurant(u).getPrice();
		} catch (InvalidRestaurantID e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double value = b*x+a;
		if (value<1) 
			return 1;
		if(value>5)
			return 5;
		
		return value;		
		 
	}
	
		
	

}
