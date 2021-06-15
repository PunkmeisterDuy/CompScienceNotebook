package Comsc76.C13;
/*
 * Duy Nguyen
 * TestGeometricObjects.java
 * Asks about triangle properties, calculates triangle's area/perimeter, and outputs properties
 */

import java.util.Scanner;

public class TestGeometricObjects {
    public static void main(String[] args) {

        Triangle triangle = new Triangle();

        Scanner input = new Scanner(System.in);
        // asks for triangle side input in prompt loop for valid triangle
        while (!triangle.isValidTriangle()) {
            System.out.print("Enter sides of a valid triangle: ");
            for (int i = 0; i < 3; i++) {
                triangle.setTrigangleSide(input.nextInt(), i);
            }
            if (!triangle.isValidTriangle()) {
                System.out.println("Unable to create triangle with those sides");
            }
        }
        System.out.print("Enter color of triangle: ");
        triangle.setColor(input.next());

        System.out.print("Is triangle filled (true/false): ");
        triangle.setFilled(input.nextBoolean());

        Circle circle = new Circle(6);
        System.out.println(circle.toString());



    }
}

class Triangle extends GeometricObject {
    int[] triangleSides = new int[3];
    double area;
    double perimeter;

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    void setTrigangleSide(int side, int index) {
        triangleSides[index] = side;
    }

    double calculateArea() {
        // Heron's formula
        int a = triangleSides[0];
        int b = triangleSides[1];
        int c = triangleSides[2];
        double s = calculatePerimeter() / 2;

        area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return area;
    }

    double calculatePerimeter() {
        int a = triangleSides[0];
        int b = triangleSides[1];
        int c = triangleSides[2];
        perimeter = a + b + c;
        return perimeter;
    }

    boolean isValidTriangle() {

        int a = triangleSides[0];
        int b = triangleSides[1];
        int c = triangleSides[2];

        if ((a + b > c) && (a + c > b) && (b + c > a)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "dumb";
    }
}

class Circle extends GeometricObject{
    int radius;
    double area;
    double perimeter;

    Circle(int radius) {
        this.radius = radius;
        calculateArea(radius);
        calculatePerimeter(radius);
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double getPerimeter() {
        return perimeter;
    }

    double calculateArea(int radius) {
        area = (Math.PI) * (radius^2);
        return area;
    }

    double calculatePerimeter(int radius) {
        perimeter = (Math.PI) * (2 * radius);
        return radius;
    }

    public String toString() {
        return "Circle: \n" + "Circle Area: " + getArea() + "\nCircle Perimeter: " + getPerimeter();
    }
}

class Rectangle extends GeometricObject{
    int[] sides = new int[4];
    double area;
    double perimeter;

    Rectangle() {
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return 0;
    }

    public String toString() {
        return "Rectangle: \n" + "Rectangle Area: " + getArea() + "\nRectangle Perimeter: " + getPerimeter();
    }
}

abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    /**
     * Construct a default geometric object
     */
    protected GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /**
     * Construct a geometric object with color and filled value
     */
    protected GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    /**
     * Return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set a new color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Return filled. Since filled is boolean,
     * the get method is named isFilled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Set a new filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Get dateCreated
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Return a string representation of this object
     */
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color +
                " and filled: " + filled;
    }

    /**
     * Abstract method getArea
     */
    public abstract double getArea();

    /**
     * Abstract method getPerimeter
     */
    public abstract double getPerimeter();
}