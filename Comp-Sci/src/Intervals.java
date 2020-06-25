/*
 * Duy Nguyen
 * Intervals.java
 * Calculates intervals between start/end times and then compares them.
 */

import java.util.Scanner;

public class Intervals {

    public static int timeConverter(String stringTime) {
        while (stringTime.indexOf("0") == 0) {
            stringTime = stringTime.substring(1);
        }
        int time = Integer.parseInt(stringTime);
        return time;
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
        int startTime1 = timeConverter(strStartTime1);
        int startTime2 = timeConverter(strStartTime1);
        int endTime1 = timeConverter(strStartTime1);
        int endTime2 = timeConverter(strStartTime1);



        System.out.println(timeConverter(strStartTime1));
        System.out.println(timeConverter(strEndTime1));



    }
}
