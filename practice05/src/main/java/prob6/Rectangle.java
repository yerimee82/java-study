package prob6;

public class Rectangle extends Shape implements Resizable {
    public Rectangle(double width, double height) {
        super(width, height);
    }

    @Override
    public void resize(double s) {
        this.width = width * s;
        this.height = height * s;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return (width + height) * 2;
    }
}
