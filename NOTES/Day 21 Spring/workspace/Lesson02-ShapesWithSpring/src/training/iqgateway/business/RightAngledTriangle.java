package training.iqgateway.business;

public class RightAngledTriangle extends Shapes {

	private double sideA, sideB;

	public RightAngledTriangle() {
		super();
	}

	public RightAngledTriangle(double sideA, double sideB) {
		super();
		this.sideA = sideA;
		this.sideB = sideB;
	}

	public double getSideA() {
		return sideA;
	}

	public void setSideA(double sideA) {
		this.sideA = sideA;
	}

	public double getSideB() {
		return sideB;
	}

	public void setSideB(double sideB) {
		this.sideB = sideB;
	}

	@Override
	public String toString() {
		return "RightAngledTriangle [sideA=" + sideA + ", sideB=" + sideB + "]";
	}

	@Override
	public double getArea() {
		return 0.5 * sideA * sideB;
	}

}
