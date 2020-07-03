/*
 * Duy Nguyen
 * LockerPuzzle.java
 * Solves Locker Puzzle using array manipulation.
 */

public class LockerPuzzle {
    public static void main(String[] args) {

        boolean lockers[] = new boolean[100];

        final int NUMLOCKERS = lockers.length;

        // Initializes each locker as closed
        for (int i = 0; i < NUMLOCKERS; i++) {
            lockers[i] = false;
        }

        // For every locker: student will negate locker status
        for (int lockerIdx = 1; lockerIdx <= NUMLOCKERS; lockerIdx++) {

            for (int studentIdx = 1;
                 (lockerIdx * studentIdx - 1 < NUMLOCKERS);
                 studentIdx++) {

                lockers[lockerIdx * studentIdx - 1] = !lockers[lockerIdx * studentIdx - 1];
            }
        }

        // Prints out formatted status of each locker, and
        for (int i = 0; i < NUMLOCKERS; i++) {
            if (lockers[i]) {
                System.out.printf("\nLocker %d is open", i + 1);
            }
            else {
                System.out.printf("\nLocker %d is closed", i + 1);
            }
        }

    }
}
