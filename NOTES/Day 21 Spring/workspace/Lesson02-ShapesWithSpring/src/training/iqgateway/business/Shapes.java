package training.iqgateway.business;

public abstract class Shapes {
	
	public abstract double getArea();

	public void printInfo() {
		System.out.printf("%s With the area o %.2f%n", getClass().getSimpleName(), getArea());
	}
}
