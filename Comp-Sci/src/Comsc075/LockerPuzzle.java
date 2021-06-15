package Comsc075;/*
 * Duy Nguyen
 * Comsc075.LockerPuzzle.java
 * Solves Locker Puzzle using array manipulation.
 */

public class LockerPuzzle {
    public static void main(String[] args) {

        final int NUM_LOCKERS = 100;
        final int STUDENTS = 100;

        boolean lockers[] = new boolean[NUM_LOCKERS];

        // For every locker: student will negate locker status
        for (int studentIdx = 1; studentIdx <= STUDENTS; studentIdx++) {

            for (int lockerIdx = studentIdx; (lockerIdx <= NUM_LOCKERS);
                 lockerIdx += studentIdx) {

                lockers[lockerIdx - 1] = !lockers[lockerIdx - 1];
            }
        }

        // Prints out formatted status of each locker
        System.out.print("Open Lockers: ");

        for (int i = 0; i < NUM_LOCKERS; i++) {
            if (lockers[i]) {
                System.out.print(i + 1 + " ");
            }
        }
        System.out.println();

    }
}
