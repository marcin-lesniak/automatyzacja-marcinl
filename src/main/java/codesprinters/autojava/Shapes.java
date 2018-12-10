package codesprinters.autojava;

import java.util.ArrayList;

public class Shapes {

	/**
	 * @param args
	 *
	 * We define set of geometrical shapes:
	 * 		rectangle (5 by 6)
	 * 		square (side is 3)
	 * 		triangle (with each side equal to 2)
	 * 		circle (radius is equal to 3)
	 * 		hex (with side equal 1)
	 * 	Application should calculate area for each of shape and then compare each pair of shapes,
	 * 	pointing out the one with larger area
	 */
	public static void main(String[] args) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		
		Rectangle rectangle1 = new Rectangle(5.0, 6.0);
		System.out.println("Created " + rectangle1.getName() + " with sides : " + rectangle1.getA()
				+ "/" + rectangle1.getB());

		Square square1 = new Square(3.0);
        System.out.println("Created " + square1.getName() + " with side : " + square1.getSide());

		Triangle triangle1 = new Triangle(2.0, 2.0, 2.0);
		System.out.println("Created " + triangle1.getName() + " with sides : " + triangle1.getA() + "/"
				+ triangle1.getB() + "/" + triangle1.getC() );

		Circle circle1 = new Circle(3.0);
        System.out.println("Created " + circle1.getName() + " with radious : " + circle1.getR());

		Hex hex1 = new Hex(1.0);
		System.out.println("Created " + hex1.getName() + " with side : " + hex1.getSide());

		shapes.add(rectangle1);;
		shapes.add(square1);
		shapes.add(triangle1);
		shapes.add(circle1);
		shapes.add(hex1);
		
		for (Shape s: shapes) {
			System.out.println(s.getName() + " " + s.getArea());
		}

		int shapesSize = shapes.size();
		for (int i=0; i<shapesSize; i++) {
			Shape firstShape = shapes.get(i);
			for (int j=i+1; j<shapesSize; j++) {
				Shape secondShape = shapes.get(j);
				System.out.println("Comparing " + getShapeNameAndArea(firstShape)
						+ " with " + getShapeNameAndArea(secondShape)
						+ " -> " + larger(firstShape, secondShape).getName());
			}
		}
	}

	static private String getShapeNameAndArea(Shape s) {
		return s.getName() + "(" + s.getArea() + ")";
	}
	
	static private Shape larger(Shape s1, Shape s2) {
		return s1.getArea() >= s2.getArea() ? s1 : s2;
	}

}
