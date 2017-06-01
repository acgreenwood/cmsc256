package A1_Q3;

/**
 * An EquilateralTriangle class that extends the IsoscelesTriangle class and implements the Polygon interface.
 * This class takes in the side lengths of the Triangle and
 * uses these values to calculate perimeter and area.
 * 
 * @author Andrew Greenwood
 */

public class EquilateralTriangle extends IsoscelesTriangle implements Polygon {

	/**
	 * Construct a new EquilateralTriangle with values input by the user.
	 * n.b.: because an Equilateral Triangle has 3 sides with equivalent length, only 1 length input is needed.
	 * @param a		The length of sides A, B, and C of the Triangle
	 */
	public EquilateralTriangle(double a) {
		//Uses constructor from superclass
		super(a, a);
	}
	
	/**
	 * @return the area of the triangle
	 */
	@Override
	public double area() {
		//Uses method from superclass
		return super.area();
	}

	/**
	 * @return the perimeter of the triangle
	 */
	@Override
	public double perimeter() {
		//Uses method from superclass
		return super.perimeter();
	}
	
	/**
	 * Allows the user to change the side length of the triangle.
	 * @param a		The new value for sides A, B, and C.
	 */
	public void setSideLength(double a) {
		//Uses variables from superclass
		super.sideA = a;
		super.sideB = a;
		super.sideC = a;
	}

}

