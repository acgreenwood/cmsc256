package A1_Q3;
/**
 * A Quadrilateral class that implements the Polygon interface.
 * This class takes in the side lengths and the two opposing angles from 
 * a quadrilateral and uses these values to calculate perimeter and area.
 * 
 * @author Andrew Greenwood
 */

public class Quadrilateral implements Polygon {
	
	protected static final double DEGTORAD = 0.0174532925; //Conversion constant from degrees to radians

	protected double sideA; //The first side of the quadrilateral
	protected double sideB; //The second side of the quadrilateral
	protected double sideC; //The third side of the quadrilateral
	protected double sideD; //The fourth side of the quadrilateral
	protected double angleAB; //The angle between sides A and B of the quadrilateral
	protected double angleCD; // The angle between sides C and D of the quadrilateral
	
	/**
	 * Construct a new quadrilateral with default values.
	 * All sides default to length 1 and opposing angles default to 90 degrees.
	 * This essentially creates a square with side length 1.
	 */
	public Quadrilateral() {
		this.sideA = 1;
		this.sideB = 1;
		this.sideC = 1;
		this.sideD = 1;
		this.angleAB = 90;
		this.angleCD = 90;
	}
	
	/**
	 * Construct a new quadrilateral with values input by the user.
	 * @param a		The length of side A
	 * @param b		The length of side B
	 * @param c		The length of side C
	 * @param d		The length of side D
	 * @param ab	The degree of the angle between sides A and B
	 * @param cd	The degree of the angle between sides C and D
	 */
	public Quadrilateral(double a, double b, double c, double d, double ab, double cd) {
		this.sideA = a;
		this.sideB = b;
		this.sideC = c;
		this.sideD = d;
		this.angleAB = ab;
		this.angleCD = cd;
	}
	
	/**
	 * @return		The area of the quadrilateral.
	 */
	@Override
	public double area() {
		//This method uses Bretschneider's Formula to calculate area of a quadrilateral given all 4 sides and 2 opposing angles
		double x = (this.perimeter()/2.0); //This is the semiperimeter of the quadrilateral
		double y = Math.pow((Math.cos(((this.angleAB*DEGTORAD)+(this.angleCD*DEGTORAD))/2.0)), 2); //This is the square of the cosine of the average of the 2 opposing angles
		double z = (x-this.sideA)*(x-this.sideB)*(x-this.sideC)*(x-this.sideD); //This is the product of the differences between the semiperimeter and each side
		return Math.sqrt(z - (this.sideA*this.sideB*this.sideC*this.sideD*y));
	}
	
	/**
	 * @return		The perimeter of the quadrilateral.
	 */
	@Override
	public double perimeter() {
		//This method simply adds the lengths of the 4 sides to get the perimeter
		return this.sideA+this.sideB+this.sideC+this.sideD;
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
	
	/**
	 * Allows the user to change the length of side D.
	 * @param in	The new value for side D.
	 */
	public void setD(double in) {
		this.sideD = in;
	}
	
	/**
	 * Allows the user to change the degree of angle AB.
	 * @param in	The new value for angle AB.
	 */
	public void setAB(double in) {
		this.angleAB = in;
	}
	
	/**
	 * Allows the user to change the degree of angle CD.
	 * @param in	The new value for angle CD.
	 */
	public void setBC(double in) {
		this.angleCD = in;
	}
}
