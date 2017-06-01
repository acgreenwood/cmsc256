package A1_Q3;
/**
 * An IsoscelesTriangle class that extends the Triangle class and implements the Polygon interface.
 * This class takes in the side lengths of the Triangle and
 * uses these values to calculate perimeter and area.
 * 
 * @author Andrew Greenwood
 */

public class IsoscelesTriangle extends Triangle implements Polygon {

	/**
	 * Construct a new IsoscelesTriangle with values input by the user.
	 * n.b.: because an Isosceles Triangle has 2 sides with equivalent length, only 2 length inputs are needed.
	 * @param a		The length of sides A and B of the Triangle
	 * @param c		The length of side C of the Triangle
	 */
	public IsoscelesTriangle(double a, double c) {
		//Uses constructor from superclass
		super(a, a, c);
	}
	
	/**
	 * @return the area of the IsoscelesTriangle
	 */
	@Override
	public double area() {
		//Uses method from superclass
		return super.area();
	}

	/**
	 * @return the perimeter of the IsoscelesTriangle
	 */
	@Override
	public double perimeter() {
		//Uses method from superclass
		return super.perimeter();
	}
	
	/**
	 * Allows the user to change the length of sides A and B.
	 * @param a		The new value for sides A and B.
	 */
	public void setA(double a) {
		//Uses variables from superclass
		super.sideA = a;
		super.sideB = a;
	}
	
	/**
	 * Allows the user to change the length of side C.
	 * @param c		The new value for side C.
	 */
	public void setC(double c) {
		//Uses variable from superclass
		super.sideC = c;
	}

}
