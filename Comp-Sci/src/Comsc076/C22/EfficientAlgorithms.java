package Comsc076.C22;

import java.util.*;

/*
 * Names: Joyee Chen, Surabhi Gupta, Duy Nguyen, Sophia Syed
 * Assignment number: 7
 * Finds the closest pair of 100 points using divide and conquer algorithm
 */

public class EfficientAlgorithms{
    public static void main(String [] args){

        //list the original 100 random points
        Point[] arrayOfPoints = new Point[100];
        for (int i = 0; i < 100; i++){
            //note: there were no restrictions mentioned in the official program
            //specification about x and y values for points (like minima and maxima),
            //but here I will generate each x and y value from between 0.0 and 100.0
            Random rnd = new Random();
            Double randX = 100 * rnd.nextDouble();
            Double randY = 100 * rnd.nextDouble();

            Point onePoint = new Point(randX, randY);
            arrayOfPoints[i] = onePoint;
        }

        System.out.println("The original 100 random points:");
        for (Point p: arrayOfPoints){
            System.out.print("(" + p.getX() + ", " + p.getY() + ")");
            System.out.println();
        }


        // the following lines are test cases
        //arrayOfPoints[50] = new Comsc076.C22.Point(49.9999, 0);
        //arrayOfPoints[49] = new Comsc076.C22.Point(50.001, .00001);

        // brute force algorithm
        long bruteStartTime = System.currentTimeMillis();
        Pair bruteClosestPair = closestPairBruteForce(arrayOfPoints);
        long bruteEndTime = System.currentTimeMillis();

        System.out.println("\nBrute Force Algorithm:");
        System.out.printf("The shortest distance is %f between the points\n", bruteClosestPair.getDistance());
        System.out.println(bruteClosestPair);

        System.out.printf("Execution time for brute force algorithm: %d milliseconds\n",
                (bruteEndTime - bruteStartTime));


        // divide and conquer algorithm
        long startTime = System.currentTimeMillis();
        Pair closestPair = getClosestPair(arrayOfPoints);
        long endTime = System.currentTimeMillis();

        System.out.println("\nDivide and Conquer Algorithm:");
        System.out.printf("The shortest distance is %f between the points\n", closestPair.getDistance());
        System.out.println(closestPair);

        System.out.printf("Execution time for divide-and-conquer algorithm: %d milliseconds",
                (endTime - startTime));

    }

    public static Pair getClosestPair(double [][] points) {
        Point[] pointsOrderedOnX = new Point[points.length];
        for (int i = 0; i < pointsOrderedOnX.length;) {
            pointsOrderedOnX[i] = new Point(points[i][0], points[i][1]);
        }

        Arrays.sort(pointsOrderedOnX, new CompareX());
        return getClosestPair(pointsOrderedOnX);
    }


    public static Pair getClosestPair(Point[] points) {

        Point[] pointsOrderedOnY = points.clone();
        Arrays.sort(pointsOrderedOnY, new CompareY());

        return Pair.distance(points, 0, points.length - 1, pointsOrderedOnY);
    }

    // gets closest pair using brute force method
    public static Pair closestPairBruteForce(double[][] points){
        Point[] p = new Point[points.length];
        for(int i = 0; i < p.length; i ++)
            p[i] = new Point(points[i][0] , points[i][1]);
        return closestPairBruteForce(p);
    }
    public static Pair closestPairBruteForce(Point[] points) {

        if(points.length < 2)
            return null;
        // p1 and p2 are the indices in the points array
        Pair p = new Pair(points[0], points[1]);
        double shortestDistances = Pair.distance(p.getP1(), p.getP2()); // initialize shortestDistance
        // Compute distance for every two points
        for(int i = 0; i < points.length; i ++){
            for(int j = i + 1; j < points.length; j++){
                double distance = Pair.distance(points[i], points[j]); // Find distance
                if(distance < shortestDistances){
                    p.setP1(points[i]);
                    p.setP2(points[j]);
                    shortestDistances = distance; // update the shortestDistance
                }
            }
        }
        return p;
    }
}

class Point{
    private double x;
    private double y;

    public Point(){
        //default set x,y to zero
        this.x = 0.0;
        this.y = 0.0;
    }
    public Point(double xVal, double yVal){
        this.x = xVal;
        this.y = yVal;
    }
    //accessor methods below
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    //mutator methods below
    public void setX(double newX){
        this.x = newX;
    }
    public void setY(double newY){
        this.y = newY;
    }
}

class CompareX implements Comparator<Point>{
    public int compare(Point p1, Point p2){
        Double p1X = p1.getX();
        Double p2X = p2.getX();
        int xComp = p1X.compareTo(p2X);
        if (xComp == 0){
            Double p1Y = p1.getY();
            Double p2Y = p2.getY();
            return p1Y.compareTo(p2Y);
        } else{
            return xComp;
        }
    }
}
//Comsc076.C22.Comsc076.C22.CompareY is for the second half of the output that will finally be printed
//in the main method
class CompareY implements Comparator<Point>{
    public int compare(Point p1, Point p2){
        Double p1Y = p1.getY();
        Double p2Y = p2.getY();
        int yComp = p1Y.compareTo(p2Y);
        if (yComp == 0){
            Double p1X = p1.getX();
            Double p2X = p2.getX();
            return p1X.compareTo(p2X);
        }else{
            return yComp;
        }
    }
}

class Pair {
    private Point p1;
    private Point p2;

    public Pair(Point p1, Point p2)
    {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }
    public Point getP1()
    {
        return p1;
    }
    public void setP2(Point p2)
    {
        this.p2 = p2;
    }
    public Point getP2()
    {
        return p2;
    }

    public double getDistance()
    {
        return distance(p1, p2);
    }
    public static double distance(Point p1, Point p2)
    {
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(
                Math.pow((x1 - x2), 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public String toString() {
        return("(" + p1.getX() + ", " + p1.getY() + ") " +
                "(" + p2.getX() + ", " + p2.getY() + ")");
    }

    // recursive method that returns distance of the closest pair of points
    public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {

        if (low >= high) {
            return null;
        } else {
            if (low + 1 == high) {
                return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
            }
        }

        /* Step 2: Divide into 2 subsets s1 (left pairs) s2 (right pairs)
         * d1 --> s1.getDistance()
         * d2 --> s2.getDistance()
         */
        int midPoint = (low + high) / 2;
        // S1: closest pair on the left side; including midpoint
        Pair s1 = distance(pointsOrderedOnX, low, midPoint, pointsOrderedOnY);
        // S2: closest pair on the right side: excluding midpoint
        Pair s2 = distance(pointsOrderedOnX, midPoint + 1, high, pointsOrderedOnY);

        double distance = 0;
        Pair p = null;

        if (s1 == null && s2 == null) { // no pairs present on either side
            distance = Double.MAX_VALUE;
        } else if (s1 == null) { 		// no pairs on left; right pair is shortest
            distance = s2.getDistance();//d2
            p = s2;
        } else if (s2 == null) { // no pairs on right; left pair is shortest
            distance = s1.getDistance(); // d1
            p = s1;
        } else {
            // min(d1,d2)
            distance = Math.min(s1.getDistance(), s2.getDistance());
            if (s1.getDistance() <= s2.getDistance()) {
                p = s1;
            } else {
                p = s2;

            }
            // Obtaining stripL and stripR algorithm
            ArrayList<Point> stripL = new ArrayList<Point>();
            ArrayList<Point> stripR = new ArrayList<Point>();
            for (int i = 0; i < pointsOrderedOnY.length; i++) {
                //if (p is in S1 and mid.x – p.x <= d) --> append p to stripL
                if ((pointsOrderedOnY[i].getX() <= pointsOrderedOnX[midPoint].getX()) &&
                        (pointsOrderedOnY[i].getX() - pointsOrderedOnX[midPoint].getX() <= distance)) {
                    stripL.add(pointsOrderedOnY[i]);
                    // else if (p is in S2 and p.x - mid.x <= d) --> append p to stripR
                } else if ((pointsOrderedOnY[i].getX() > pointsOrderedOnX[midPoint].getX()) &&
                        (pointsOrderedOnY[i].getX() - pointsOrderedOnX[midPoint].getX() <= distance)){
                    stripR.add(pointsOrderedOnY[i]);
                }
            }

            //Step 3: Find the closest pair for a point in stripL and for a point in stripR
            double d3 = distance;

            int r = 0;

            for(int i = 0; i< stripL.size(); i++) {
                while (r < stripR.size() &&
                        stripL.get(i).getY() > stripR.get(r).getY() + distance) {
                    r++;
                }

                int r1 = r;
                while (r1 < stripR.size() && stripR.get(r1).getY() <= stripL.get(i).getY() + distance) {

                    // Check if (stripL.get(i), stripR.get(r1)) is a possible closest pair
                    if (d3 > distance(stripL.get(i), stripR.get(r1))) {
                        d3 = distance(stripL.get(i), stripR.get(r1));
                        p.p1 = stripL.get(i);
                        p.p2 = stripR.get(r1);
                    }
                    r1++;
                }
            }
        }

        return p;
    }
}