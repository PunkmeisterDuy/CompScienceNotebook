/*
 * Duy Nguyen
 * Intervals.java
 * Calculates intervals between start/end times and then compares them.
 */

import java.util.Scanner;

public class Intervals {

    public static int octalConverter(String stringTime) {
        // Used to avoid octal numbers with integers starting with 0
        while (stringTime.indexOf("0") == 0) {
            stringTime = stringTime.substring(1);
        }

        if (stringTime.equals("")) { // Makes 0000 = 2400 to avoid runtime error
            stringTime = "2400";
        }

        int time = Integer.parseInt(stringTime);
        return time;
    }

    public static int timeDifference(int time1, int time2) {
        // Calculates total interval in minutes
        int hours = 0;
        int minutes = 0;
        int interval = 0;

        if (time1 <= time2) {
            hours = (time2 / 100) - (time1 / 100) - 1;
        }
        else if (time1 > time2) {
            hours = (24 - (time1 / 100)) + (time2 / 100) - 1;
        }

        minutes = ((time2 % 100) + (60 - (time1 % 100)));
        interval = (hours * 60) + minutes;

        return interval;
    }

    public static void main(String[] args) {

        // Takes strings for time intervals to avoid octal numbers
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first start " +
                "and end time as two 24-hour format times: ");
        String strStartTime1 = input.next();
        String strEndTime1 = input.next();

        System.out.print("Enter second start " +
                "and end time as two 24-hour format times: ");
        String strStartTime2 = input.next();
        String strEndTime2 = input.next();

        //Converts time into integers to avoid Octal numbers
        int startTime1 = octalConverter(strStartTime1);
        int startTime2 = octalConverter(strStartTime2);
        int endTime1 = octalConverter(strEndTime1);
        int endTime2 = octalConverter(strEndTime2);

        // Finds time difference and calculates time interval
        int interval1 = timeDifference(startTime1, endTime1);
        int interval2 = timeDifference(startTime2, endTime2);

        System.out.println("The first interval is " + interval1 + " minutes.");
        System.out.println("The second interval is " + interval2 + " minutes.");

        // Checks which interval is greater
        if (interval1 > interval2) {
            System.out.println("The first interval is longer");
        }
        else if (interval1 < interval2) {
            System.out.println("The second interval is longer");
        }
        else {
            System.out.println("Both intervals are equal");
        }
    }
}
