package com.jdriven.jdkworkshop.demo;

import org.junit.jupiter.api.Test;

public class SealedClassesAndPatternMatchingTest {

    @Test
    public void sealedClassesInstanceOfTest() {
        var triangle = new Triangle(3, 4);
        var circle = new Circle(5);
        var rectangle = new Rectangle(10, 20);
        var square = new Square(15);
        var polygon = new WeirdlyShaped() {
            @Override
            public double getArea() {
                return 42;
            }
        };

        printShapeInfo(triangle);
        printShapeInfo(circle);
        printShapeInfo(rectangle);
        printShapeInfo(square);
        printShapeInfo(polygon);
    }

    private void printShapeInfo(Shape shape) {
        if (shape instanceof Triangle t) {
            System.out.print("Triangle with height " + t.height() + " and width " + t.width());
        } else if (shape instanceof Circle c) {
            System.out.print("Circle with radius " + c.radius());
        } else if (shape instanceof Rectangle r) {
            System.out.print("Rectangle with height " + r.getHeight() + " and width " + r.getWidth());
        } else if (shape instanceof WeirdlyShaped) {
            System.out.print("Weird shape!");
        }
        System.out.println(" - Area: " + findArea(shape));
    }

    private double findArea(Shape shape) {
        return switch (shape) {
            case Triangle t -> t.height() * t.width() / 2.0;
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.getHeight() * r.getWidth();
            case WeirdlyShaped w -> w.getArea();
        };
    }
}

/**
 * Base sealed interface for all shapes.
 */
sealed interface Shape permits Rectangle, Triangle, Circle, WeirdlyShaped { }

/**
 * Triangle shape implementation. Records are final, so no subclasses possible.
 */
record Triangle(int height, int width) implements Shape { }

/**
 * Circle shape implementation. Records are final, so no subclasses possible.
 */
record Circle(int radius) implements Shape { }

/**
 * Rectangle shape implementation, is sealed, so there is one possible subclass: Square.
 */
sealed class Rectangle implements Shape permits Square {
    private final int height;
    private final int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

/**
 * Square rectangle implementation, is final, so no subclasses possible.
 */
final class Square extends Rectangle {
    public Square(int length) {
        super(length, length);
    }
}

/**
 * Weird shape implementation, is non-sealed, so any subclass can be created.
 */
abstract non-sealed class WeirdlyShaped implements Shape {
    public abstract double getArea();
}
