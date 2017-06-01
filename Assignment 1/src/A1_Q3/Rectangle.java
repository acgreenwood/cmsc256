package A1_Q3;
/**
 * A Rectangle class that extends the Quadrilateral class and implements the Polygon interface.
 * This class takes in the side lengths of the Rectangle and
 * uses these values to calculate perimeter and area.
 * 
 * @author Andrew Greenwood
 */

public class Rectangle extends Quadrilateral implements Polygon {

	/**
	 * Construct a new Rectangle with values input by the user.
	 * n.b.: because a Rectangle has 2 pairs of equivalent and opposite sides and all internal angles are 90 degrees,
	 * only two side inputs are needed and no angle inputs are needed.
	 * @param a 	The length of sides A and C of the Rectangle
	 * @param b		The length of sides B and D of the Rectangle
	 */
	public Rectangle(double a, double b) {
		//Uses constructor from superclass
		super(a, b, a, b, 90, 90);
	}
	
	/**
	 * @return the area of the Rectangle
	 */
	@Override
	public double area() {
		//Uses method from superclass
		return super.area();
	}
	
	/**
	 * @return the perimeter of the Rectangle
	 */
	@Override
	public double perimeter() {
		//Uses method from superclass
		return super.perimeter();
	}
	
	/**
	 * Allows the user to change the side lengths of the Rectangle.
	 * @param a		The new value for sides A and C.
	 */
	public void setA(double a) {
		//Uses variables from superclass
		super.sideA = a;
		super.sideC = a;
	}
	
	/**
	 * Allows the user to change the side lengths of the Rectangle.
	 * @param a		The new value for sides B and D.
	 */
	public void setB(double b) {
		//Uses variables from superclass
		super.sideB = b;
		super.sideD = b;
	}
}
