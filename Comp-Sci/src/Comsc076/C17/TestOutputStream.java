package Comsc076.C17;

/*
 * Duy Nguyen
 * TestOutputStream.java (File I/O)
 * Writes bits represented as a String into bytes to a file
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestOutputStream {
    public static void main(String[] args) throws Exception {

        // creates output and writes bytes to it
        BitOutputStream output = new BitOutputStream(new File("testOutput.dat"));
        output.writeBit("010000100100001001101");
        output.close();
        System.out.println("Finished outputting to testOutput.dat");
    }

    public static class BitOutputStream {
        private FileOutputStream fileOutput;
        private int byteValue = 0; // value of byte
        private int count = 0; // iterate up to 8 bits
        private int mask = 1; // fills first bit of value with mask

        public BitOutputStream(File file) throws IOException {
            fileOutput = new FileOutputStream(file);
        }

        public void writeBit(String bitString) throws IOException {
            // iterates for each bit in bit string
            for (int i = 0; i < bitString.length(); i++) {
                writeBit(bitString.charAt(i));
            }
        }


        public void writeBit(char bit) throws IOException {
            count++;

            byteValue <<= 1;
            /* each time writeBit(String bitString) iterates,
             * moves current bits by bit left shifting
             * 0 0 0 0 0 0 0 1
             * 0 0 0 0 0 0 1 0
             */

            if (bit == '1') {
                byteValue |= mask;
            }
            /* if bit in iteration is 1, current value bit = 0, becomes 1 after
             * or statement of the mask.
             * bit = 1
             * 0 0 0 0 0 0 0 0 : value
             * 0 0 0 0 0 0 0 1 : mask
             * 0 0 0 0 0 0 0 1 : value after or statement
             */

            if (count == 8) { // once byte is full, write value to file
                count = 0; // resets count
                fileOutput.write(byteValue);
            }
        }

        // fills in remaining bits of last byte, and closes file output
        public void close() throws IOException {

            if (count > 0) {
                byteValue <<= (8 - count); // for each remaining bit, left shift the remaining
                fileOutput.write(byteValue);
            }

            fileOutput.close(); // Closes file output stream
        }
    }
}
