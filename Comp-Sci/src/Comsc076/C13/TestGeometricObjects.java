package Comsc076.C13;
/*
 * Duy Nguyen
 * TestGeometricObjects.java
 * Asks about triangle properties then outputs area, perimeter, color,
 * and if the triangle, a circle, and a rectangle are filled
 */

import java.util.Scanner;

public class TestGeometricObjects {
    public static void main(String[] args) {

        // takes input from console
        Scanner input = new Scanner(System.in);


        Triangle triangle = new Triangle();

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



        // Prints triangle properties
        System.out.println(triangle.toString());

        // Prints circle properties with radius of 6,
        // color purple, and filled
        Circle circle = new Circle(6, "purple", true);
        System.out.println(circle.toString());

        // Prints rectangle properties with width of 10, height of 20,
        // color blue, and filled
        Rectangle rectangle = new Rectangle(10, 20, "blue", true);
        System.out.println(rectangle.toString());



    }
}

class Triangle extends GeometricObject {
    private int[] triangleSides = new int[3];
    private double area;
    private double perimeter;

    Triangle() { //constructors in Triangle class are not used, but for standard practice
        for (int i = 0; i < 3; i++) {
            triangleSides[i] = 0;
        }
        getArea();
        getPerimeter();
    }

    Triangle(int a, int b, int c) { // a,b,c to conform to mathematical formulas
        triangleSides[0] = a;
        triangleSides[1] = b;
        triangleSides[2] = c;

        getArea();
        getPerimeter();
    }

    Triangle(int a, int b, int c, String color) {
        triangleSides[0] = a;
        triangleSides[1] = b;
        triangleSides[2] = c;

        getArea();
        getPerimeter();
        setColor(color);
    }

    Triangle(int a, int b, int c, boolean filled) {
        triangleSides[0] = a;
        triangleSides[1] = b;
        triangleSides[2] = c;

        getArea();
        getPerimeter();
        setFilled(filled);
    }

    Triangle(int a, int b, int c, String color, boolean filled) {
        triangleSides[0] = a;
        triangleSides[1] = b;
        triangleSides[2] = c;

        getArea();
        getPerimeter();
        setColor(color);
        setFilled(filled);
    }

    public double getArea() {
        // Heron's formula
        int a = triangleSides[0];
        int b = triangleSides[1];
        int c = triangleSides[2];
        double s = getPerimeter() / 2;

        // i don't know if this is bad practice to use a getter to update the instance
        area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return area;
    }

    public double getPerimeter() {
        int a = triangleSides[0];
        int b = triangleSides[1];
        int c = triangleSides[2];

        perimeter = a + b + c;
        return perimeter;
    }

    void setTrigangleSide(int side, int index) {
        triangleSides[index] = side;
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
        return "\nTriangle: " +
                "\nArea: " + Math.floor(getArea() * 100) / 100 +
                "\nPerimeter: " + Math.floor(getPerimeter() * 100) / 100 +
                "\nColor: " + getColor() +
                "\nFilled: " + isFilled();
    }
}

class Circle extends GeometricObject{
    private int radius;
    private double area;
    private double perimeter;

    Circle() {
        radius = 0;
        area = 0;
        perimeter = 0;
    }

    Circle(int radius) {
        this.radius = radius;
        getArea();
        getPerimeter();
    }

    Circle(int radius, String color) {
        this.radius = radius;
        getArea();
        getPerimeter();
        setColor(color);
    }

    Circle(int radius, boolean filled) {
        this.radius = radius;
        getArea();
        getPerimeter();
        setFilled(filled);
    }

    Circle(int radius, String color, boolean filled) {
        this.radius = radius;
        getArea();
        getPerimeter();
        setColor(color);
        setFilled(filled);
    }

    public double getArea() {
        area = (Math.PI) * (radius^2);
        return area;
    }

    public double getPerimeter() {
        perimeter = (2 * Math.PI * radius);
        return perimeter;
    }

    private void setRadius(int radius) {
        this.radius = radius;
    }

    public String toString() {
        return "\nCircle: " +
                "\nArea: " + Math.floor(getArea() * 100) / 100 +
                "\nPerimeter: " + Math.floor(getPerimeter() * 100) / 100 +
                "\nColor: " + getColor() +
                "\nFilled: " + isFilled();
    }
}

class Rectangle extends GeometricObject{
    private int width;
    private int height;
    private double area;
    private double perimeter;

    Rectangle() {
        width = 0;
        height = 0;
        area = 0;
        perimeter = 0;
    }

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;

        area = getArea();
        perimeter = getPerimeter();
    }

    Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        setColor(color);

        area = getArea();
        perimeter = getPerimeter();
    }

    Rectangle(int width, int height, boolean filled) {
        this.width = width;
        this.height = height;
        setFilled(filled);

        area = getArea();
        perimeter = getPerimeter();
    }

    Rectangle(int width, int height, String color, boolean filled) {
        this.width = width;
        this.height = height;
        setColor(color);
        setFilled(filled);

        area = getArea();
        perimeter = getPerimeter();
    }

    public double getArea() {
        area = height * width;
        return area;
    }

    public double getPerimeter() {
        perimeter = (2 * height) + (2 * width);
        return perimeter;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public String toString() {
        return "\nRectangle: " +
                "\nArea: " + getArea() +
                "\nPerimeter: " + getPerimeter() +
                "\nColor: " + getColor() +
                "\nFilled: " + isFilled();
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