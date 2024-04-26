package com.poscodx.paint.main;

import java.util.PrimitiveIterator.OfDouble;

import com.poscodx.paint.i.Drawable;
import com.poscodx.paint.point.ColorPoint;
import com.poscodx.paint.point.Point;
import com.poscodx.paint.shap.Circle;
import com.poscodx.paint.shap.Shape;
import com.poscodx.paint.text.GraphicText;

public class Main {

	public static void main(String[] args) {
		Point point = new Point(10, 20);
//		point.setX(10);
//		point.setY(20);
//		
//		drawPoint(point);;
		point.show(true);
		
		ColorPoint point2 = new ColorPoint(100, 200, "red");
//		drawPoint(point2);
//		point2.show(false);
//		draw(point2);
//		
//		drawTriangle(new Triangle());
//		drawRectangle(new Rectangle());
		
//		drawShape(new Triangle());
//		drawShape(new Rectangle());
//		drawShape(new Circle());
//		draw(new Triangle());
		draw(new GraphicText("Hello World!"));
		
		// instance of 연산자
		Circle c = new Circle();
		
		System.out.println(c instanceof Circle);
		System.out.println(c instanceof Shape);
		System.out.println(c instanceof Object);
		
		// 오류 : 연산자 우측항이 클래스 경우,
		//       레퍼런스 하고 있는 class 타입의 hierachy 상의 하위 상위
		//       instanceof 연산자를 사용할 수 있다. 
//		System.out.println(c instanceof Point);
		
		Object object = new Circle();
		System.out.println(object instanceof String);
		
		// 연산자 우측항이 인터페이스인 경우
		// Hierachry 상관없이 instanceof 연산자를 사용할 수 있다. 
		
		System.out.println(c instanceof Drawable);
		System.out.println(c instanceof Runnable);
		
		
	}
	private static void draw(Drawable drawable) {
		drawable.draw();
	}
	private static void drawPoint(Point point) {
		point.show();
	}
	
//	private static void drawColorPoint(ColorPoint colorPoint) {
//		colorPoint.show();
//	}
	private static void drawShape(Shape shape) {
		shape.draw();
	}
//	private static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
//	
//	private static void drawRectangle(Rectangle rectangle) {
//		rectangle.draw();
//	}

}
