package Comsc076.C20;

/*
 * Duy Nguyen
 * SortPoints.java (Lists, Stacks, and Queues)
 * Generates array of points with random x, y coordinates,
 * sorts by x and y, and prints the sorted lists
 */

import java.util.Arrays;
import java.util.Comparator;

public class SortPoints {
    public static void main(String[] args) {


        // generates array with 100 points with random coordinates
        Point[] points = new Point[100];

        for (int i = 0 ; i < points.length ; i++) {
            points[i] = new Point(
                                Point.generateRandomPoint(100),
                                Point.generateRandomPoint(100));
        }

        /* test cases to see if it sorts by x coordinate/y coordinate
           when one/both coordinate is equal
        points[0] = new Point(1, 3);
        points[1] = new Point(1, 2);
        points[2] = new Point(5, 4);
        points[3] = new Point(3, 4);
        points[4] = new Point(3, 4);
         */

        // sorts new array by x
        Point[] pointsSortedX = points.clone();
        Arrays.sort(pointsSortedX);

        // sorts new array by y
        Point[] pointsSortedY = points.clone();
        Arrays.sort(pointsSortedY, new CompareY());


        // outputs sorted by x array
        System.out.println("Points sorted by x coordinates");
        for (Point point : pointsSortedX) {
            System.out.println(point);
        }

        // outputs sorted by y array
        System.out.println("\nPoints sorted by y coordinates");
        for (Point point : pointsSortedY) {
            System.out.println(point);
        }

    }
}



class Point implements Comparable<Point> {

    // point coordinates
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // compares x if x is greater or less than,
    // compares y if x coordinate is equal
    //



    public int compareTo(Point point) {
        if (this.x > point.x) {
            return 1;
        } else if (this.x < point.x) {
            return -1;
        } else if (this.x == point.x) {
            return(new CompareY().compare(this, point));
        }

        return 0;
    }

    // generates random double from 0 inclusive up to range exclusive
    static double generateRandomPoint(int range) {
        return Math.random() * range;
    }


    public String toString() {
        return("x: "+ x + " y: " + y);
    }

}


class CompareY implements Comparator<Point> {

    // compares y if y is greater or less than,
    // compares x if y coordinate is equal
    @Override
    public int compare(Point point1, Point point2) {
        double point1y = point1.getY();
        double point2y = point2.getY();

        if (point1y > point2y) {
            return 1;
        } else if (point1y < point2y) {
            return -1;
        } else if (point1y == point2y) {
            // if x and y coordinates are equal, return 0
            // to prevent indirect recursion -> stackoverflow error
            if (point1.getX() == point2.getX()) {
                return 0;
            } else {
                return (point1.compareTo(point2));
            }
        }

        return 0;
    }
}
