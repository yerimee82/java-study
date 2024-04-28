package prob6;

public class RectTriangle extends Shape{
    public RectTriangle(double width, double height) {
        super(width, height);
    }

    @Override
    public double getArea() {
        return (width * height) / 2;
    }

    @Override
    public double getPerimeter() {
        double sqrt = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        return width + height + sqrt;
    }
}
