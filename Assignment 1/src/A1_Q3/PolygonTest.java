package A1_Q3;

/**
 * Class for testing all the Polygons
 * 
 * @author Andrew Greenwood
 */

public class PolygonTest {
	public static void main(String[] args) {
		System.out.println("Quadrilateral:");
		Polygon test1 = new Quadrilateral(8, 7, 5, 12, 46, 102);
		System.out.println("Perimeter: "+test1.perimeter());
		System.out.println("Area: "+test1.area());
		
		System.out.println("");
		
		System.out.println("Rectangle:");
		Polygon test2 = new Rectangle(4.5, 13);
		System.out.println("Perimeter: "+test2.perimeter());
		System.out.println("Area: "+test2.area());
		
		System.out.println("");
		
		System.out.println("Square:");
		Polygon test3 = new Square(9);
		System.out.println("Perimeter: "+test3.perimeter());
		System.out.println("Area: "+test3.area());
		
		System.out.println("");
		
		System.out.println("Triangle:");
		Polygon test4 = new Triangle(7, 9, 14);
		System.out.println("Perimeter: "+test4.perimeter());
		System.out.println("Area: "+test4.area());
		
		System.out.println("");
		
		System.out.println("IsoscelesTriangle:");
		Polygon test5 = new IsoscelesTriangle(6, 10);
		System.out.println("Perimeter: "+test5.perimeter());
		System.out.println("Area: "+test5.area());
		
		System.out.println("");
		
		System.out.println("EquilateralTriangle:");
		Polygon test6 = new EquilateralTriangle(3);
		System.out.println("Perimeter: "+test6.perimeter());
		System.out.println("Area: "+test6.area());
	}
}
	