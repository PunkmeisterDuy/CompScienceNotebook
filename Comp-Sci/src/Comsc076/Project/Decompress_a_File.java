/* 
* Surabhi Gupta and Jacob Re - Final Project (Decompress_a_File)
* The other part of this program (Compress_a_File) was completed by Duy 
* Nguyen. This program uses the concept of Huffman Coding in order to
* decompress a file that has been similarly compressed using Huffman
* Coding. To use the program, one must simply compile the java file, and,
* when executing the class file, input the compressed file and target file
* for decompression in the command line. The program first reads the 
* Huffman Key from the provided compressed file, as the Key is needed to 
* decode the compressed data that is also included in that file. The
* readHuffmanKey method simply reads the beginning of the compressed
* file and stores the key in an array to be used later. The decompressedFile
* method takes in both user inputted files. It then reads the enoded (compressed)
* text and loops through it character by character. Then, the program loops 
* until it finds the character in charCodesArray that matches the current 
* one and outputs that ASCII value to the target file. Once all of the
* compressed data has been read in this way, the streams are closed
* and the program outputs a message telling the user the file to which the
* compressed data was written.
*/

// java.io.* imported for purposes of using file streams
import java.io.*;

public class Decompress_a_File {
    // static variables
    // Number of ASCII characters
    private static final int SIZE = 256;  
    // Array to hold Huffman codes of characters
    private static String[] charCodesArray;  
    // Original size of File
    private static long originalSize; 
    // Size of the key at the beginning of compressed file
    private static long keySize;  
    // Size that has to be read
    private static long readSize = 0; 

    // main method
    public static void main(String[] args) {
        // Check the parameters and get the file name. If the user does not
        // input two text files in the command line, print out a message 
        // instructing porper usage and exit
        if ((args.length != 2)) {
            System.out.println("Usage: java Decompress_a_File input output");
            System.exit(1);
        }

        // Read the Huffman Key from the beginning of the compressed file
        readHuffmanKey(args[0]);
        // Call the decompress function with the first argument being the compressed
        // file and the second file being the target file for decompression
        decompressedFile(args[0], args[1]);
        // Print message so user knows that the file has been properly decompressed and
        // the file it was decompressed to
        System.out.println("File decompressed to " + args[1]);
    }



    // Decompress the compress file using Huffman Code
    public static void decompressedFile(String sourceFile, String destinationFile) {
        // Try/catch block to catch FileNotFound errors, IOException errors and ClassNotFound errors
        try {
            // Using BufferedInputStream to read the sourceFile (which is the compressed file)
            BufferedInputStream inputFile = new BufferedInputStream(new FileInputStream(sourceFile));
            // Using DataOutputStream to write decompressed data to file
            DataOutputStream outputFile = new DataOutputStream(new FileOutputStream(destinationFile));
            // Skip over the beginning of the compressed file to skip over Huffman Key
            inputFile.skip(keySize); 
            // Pointer used to read input file
            int pointer = 0; 
            // Using StringBuilder so that string can be more easily appended with text from compressed file
            StringBuilder encodedText = new StringBuilder();

            // Read the encoded text to String variable
            while ((pointer = inputFile.read()) != -1) {
                // Append the StringBuilder with each bit
                encodedText.append(getBits(pointer));

            }
            // Iterate encoded text character by character
            String temp = "";
            for (int i = 0; i < encodedText.length(); i++) {
                // Make sure we don't go over the end of the string
                if (readSize == originalSize) {
                    continue;
                }
                // Append temp with the current character 
                temp +=(encodedText.charAt(i)); 

                // Test the string with Huffman Code (loop through ASCII characters to make sure
                // it is found correctly)
                for (int j = 0; j < SIZE; j++) {
                    // If the value is not null and the temp value is equal to the current char
                    // code, write the ASCII character to the file
                    if ((charCodesArray[j] != null) && (temp.equals(charCodesArray[j]))) {
                        outputFile.write(j);
                        // Increment readSize and reset temp
                        readSize++;
                        temp = "";
                        continue;
                    }
                }
            }
            // Close both input and output streams
            inputFile.close();
            outputFile.close();

        // Catch block for errors
        } catch (FileNotFoundException ex) {
            System.out.println(sourceFile + "file not found");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }


    // Method to read the bits from the input file (using similar logic to that from the File I/O
    // assignment from the beginning of the course
    public static String getBits(int value) { 
        // Value that will be returned
        StringBuilder result = new StringBuilder();
        // Mask used with bitwise operations
        int mask = 1;

        // Loop through to make a whole byte
        for (int i = 7; i >= 0; i--) {
            // Initialize temp
            int temp = value >> i;
            // Get the current bit and add it to the result variable
            int bit = temp & mask;
            result.append(bit);
        }
        // Return the result and case to string 
        return result.toString();
    }




    // Read the Huffman Key and original file size from the input file. Function needed to avoid invalid
    // stream header as well as to obtain the Huffman Key to be used in decoding
    public static void readHuffmanKey(String sourceFile) {
        // Try/catch block to catch FileNotFound errors, IOException errors and ClassNotFound errors
        try {
            // Using ObjectInputStream to read Huffman Key from compressed file
            ObjectInputStream inputKey = new ObjectInputStream(new FileInputStream(sourceFile));
            // Initialize originalSize with size of file
            originalSize = inputKey.readLong(); 
            // Read the Huffman Key and store it in charCodesArray
            charCodesArray = (String[])(inputKey.readObject()); 
            // Since there are 2 bytes between the key and the compressed data, have keySize be equal
            // to the size of the key plus the two additional bytes so that we read data first
            keySize = (inputKey.readLong()) + (Long.SIZE) / 8 + 2; 
            // Close ObjectInputStream
            inputKey.close();

        // Catch blocks for errors
        } catch (FileNotFoundException ex) {
            System.out.println(sourceFile + "File not found.");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
