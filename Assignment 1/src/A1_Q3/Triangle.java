package A1_Q3;
/**
 * A Triangle class that implements the Polygon interface.
 * This class takes in the side lengths of the Triangle and
 * uses these values to calculate perimeter and area.
 * 
 * @author Andrew Greenwood
 */

public class Triangle implements Polygon {

	protected double sideA; //The first side of the Triangle
	protected double sideB; //The second side of the Triangle
	protected double sideC; //The third side of the Triangle
	
	/**
	 * Construct a new Triangle with default values.
	 * All sides default to length 1.
	 * This creates an equilateral triangle with side length 1.
	 */
	public Triangle() {
		this.sideA = 1;
		this.sideB = 1;
		this.sideC = 1;
	}
	
	/**
	 * Construct a new Triangle with values input by the user.
	 * 
	 * @param a		The length of side A.
	 * @param b		The length of side B.
	 * @param c		The length of side C.
	 */
	public Triangle(double a, double b, double c) {
		this.sideA = a;
		this.sideB = b;
		this.sideC = c;
	}
	
	/**
	 * @return the area of the Triangle
	 */
	@Override
	public double area() {
		//This method uses Heron's Formula to calculate area given all 3 sides
		double x = (this.perimeter()/2.0); //This is the semiperimeter of the triangle
		return Math.sqrt(x*(x-this.sideA)*(x-this.sideB)*(x-this.sideC));
	}

	/**
	 * @return the perimeter of the Triangle
	 */
	@Override
	public double perimeter() {
		//This method simply adds the 3 side lengths together to get the perimeter
		return this.sideA+this.sideB+this.sideC;
	}
	
	/**
	 * Allows the user to change the length of side A.
	 * @param in	The new value for side A.
	 */
	public void setA(double in) {
		this.sideA = in;
	}
	
	/**
	 * Allows the user to change the length of side B.
	 * @param in	The new value for side B.
	 */
	public void setB(double in) {
		this.sideB = in;
	}
	
	/**
	 * Allows the user to change the length of side C.
	 * @param in	The new value for side C.
	 */
	public void setC(double in) {
		this.sideC = in;
	}
}
