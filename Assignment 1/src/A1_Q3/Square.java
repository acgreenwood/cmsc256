package A1_Q3;

/**
 * A Square class that extends the Rectangle class and implements the Polygon interface.
 * This class takes in the side lengths of the Square and
 * uses these values to calculate perimeter and area.
 * 
 * @author Andrew Greenwood
 */

public class Square extends Rectangle implements Polygon {

	/**
	 * Construct a new Square with values input by the user.
	 * n.b.: because all sides of a square are the same lengths and all internal angles are 90 degrees,
	 * only 1 input is needed
	 * @param a 	The side lengtht of the square
	 */
	public Square(double a) {
		//Uses constructor from superclass
		super(a, a);
	}

	/**
	 * @return the area of the square
	 */
	@Override
	public double area() {
		//Uses method from superclass
		return super.area();
	}
	
	/**
	 * @return the perimeter of the square
	 */
	@Override
	public double perimeter() {
		//Uses method from superclass
		return super.perimeter();
	}
	
	/**
	 * Allows the user to change the side lengths of the Square.
	 * @param a		The new value for all 4 sides
	 */
	public void setA(double a) {
		//Uses variables from superclass
		super.sideA = a;
		super.sideB = a;
		super.sideC = a;
		super.sideD = a;
	}
}
