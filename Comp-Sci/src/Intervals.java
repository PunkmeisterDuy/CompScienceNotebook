/*
 * Duy Nguyen
 * Intervals.java
 * Calculates intervals between start/end times and then compares them.
 */

import java.util.Scanner;

public class Intervals {

    public static int octalConverter(String stringTime) {
        //Used to avoid octal numbers with integers starting with 0
        while (stringTime.indexOf("0") == 0) {
            stringTime = stringTime.substring(1);
        }
        if (stringTime.equals("")) { // Makes 0000 = 2400 instead
            stringTime = "2400";
        }
        int time = Integer.parseInt(stringTime);
        return time;
    }

    public static int timeDifference(int time1, int time2) {
        //Modulate them here
        int interval = 0;

        if (time1 < time2) {
            interval = (2400 - time1) - (2400 - time2);
        }
        else if (time1 > time2) {
            interval = (2400 - time1) + time2;
        }
        else if (time1 == time2) {
            interval = 2400;
        }
        else {
            System.out.println("Error, input times are not valid");
        }
        return interval;
    }

    public static void main(String[] args) {

        // Takes Input for times
        Scanner input = new Scanner(System.in);

        System.out.print("Enter earlier start and end time as two 24-hour format times: ");
        String strStartTime1 = input.next();
        String strEndTime1 = input.next();

        System.out.print("Enter later start and end time as two 24-hour format times: ");
        String strStartTime2 = input.next();
        String strEndTime2 = input.next();

        //Converts Time to avoid Octal numbers
        int startTime1 = octalConverter(strStartTime1);
        int startTime2 = octalConverter(strStartTime2);
        int endTime1 = octalConverter(strEndTime1);
        int endTime2 = octalConverter(strEndTime2);

        // Finds time difference and calculates time interval
        int interval1 = timeDifference(startTime1, endTime1);
        int interval2 = timeDifference(startTime2, endTime2);

        System.out.println(interval1);
        System.out.println(interval2);

    }
}
