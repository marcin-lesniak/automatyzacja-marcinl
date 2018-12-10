package codesprinters.autojava;

public class Circle extends AbstractShape {

	private double r;

	public Circle (double r) {
		this.r = r;
	}

	public double getR() {
		return r;
	}

	@Override
	public double getArea() {
		return Math.PI*r*r;
	}

}
