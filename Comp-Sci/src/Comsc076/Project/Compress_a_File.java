/*
 * Duy Nguyen, Joyee Chen
 * Compress_a_File.java
 * Gets console input of text file, and returns compressed version using Huffman Code
 */

import java.io.*;

public class Compress_a_File {
    // Array to store Huffman Codes for each character
    private static String[] codes;

    public static void main(String[] args) throws IOException {

        // Output correct usage method if user does not input 2 files
        if (args.length != 2) { //duy
            System.out.println("Usage: java Compress_a_File inputFile compressedFile");
            System.exit(1);
        }

        // joyee
        //based on the sample command line input the professor gave us:
        //    C:\Users\.... > java Compress_a_File sourceFile.txt compressedFile.txt
        //String sourcefile.txt is args[0] and String compressedFile.txt is args[1]

        String inputFile = args[0];
        String outputFile = args[1];

        //first, we translate sourceFile.txt into a Huffman tree
        int[] counts = getFileCharFrequency(inputFile);

        HuffmanTree huffmanTree = HuffmanTree.getHuffmanTree(counts);
        HuffmanTree.getHuffmanCode(huffmanTree.root);

        // duy
        // Second, we output the Huffman codes into the compressedFile
        // write original file size -> charCodesArray -> size of key part
        writeCodes(inputFile, outputFile, codes);

        // We assume data is not to be appended to the existing file, because there might be accidents when the
        // decompression algorithm may mistake successive Huffman codes and successive compressed messages
        // as part of the first compressed message

        // Third, we output the encoded binary contents as bits/bytes into the compressedFile
        writeCompressedData(inputFile, outputFile, codes);

        System.out.printf("Finished Compressing: %s to %s \n", inputFile, outputFile);
    }

    public static int[] getFileCharFrequency(String file) {
        // Used to store how many times each character occurs (256 is the number of ASCII characters)
        int[] counts = new int[256];
        int tempChar = 0;
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

            // Read whole file and store counts for each character occurance
            while ((tempChar = input.read()) != -1) {
                counts[tempChar]++;
            }
            input.close();

            return counts;
        } catch (FileNotFoundException ex) {
            System.out.printf("%s not found", file);
            ex.printStackTrace(System.out);
            System.exit(1);
        } catch (IOException ex) {
            System.out.printf("Failed/Interrupted IO Operation");
            ex.printStackTrace(System.out);
            System.exit(1);
        }
        return counts;
    }

    public static void writeCodes(String inputFile, String outputFile, String[] codes) {
        try {
            // Writing the Huffman Codes to compressed file
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(outputFile));
            long inputSize = new File(inputFile).length();
            // Write the size of the file and the codes to the output
            output.writeLong(inputSize);
            output.writeObject(codes);

            // Get the size of the Huffman key
            long keySize = new File(outputFile).length();
            output.writeLong(keySize);
            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println(inputFile + " file not found");
            ex.printStackTrace(System.out);
            System.exit(1);
        } catch (IOException ex) {
            System.out.printf("Failed/Interrupted IO Operation");
            ex.printStackTrace(System.out);
            System.exit(1);
        }
    }

    public static void writeCompressedData(String inputFile, String outputFile, String[] codes) {
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFile));
            BitOutputStream output = new BitOutputStream(new File(outputFile), true);
            int pointer = 0; // pointer in the input file

            // writes each corresponding code from input file
            while ((pointer = input.read()) != -1) {
                output.writeBit(codes[pointer]);
            }

            output.close();
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println(inputFile + " file not found");
            ex.printStackTrace(System.out);
            System.exit(1);
        } catch (IOException ex) {
            System.out.printf("Failed/Interrupted IO Operation");
            ex.printStackTrace(System.out);
            System.exit(1);
        }
    }

    // Class to represent the Huffman Tree
    public static class HuffmanTree implements Comparable<HuffmanTree> {
        Node root; // The root of the tree

        // Create a Huffman tree with two subtrees
        public HuffmanTree(HuffmanTree t1, HuffmanTree t2) {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        // Huffman tree constructor - Tree containing a leaf node
        public HuffmanTree(int weight, char element) {
            root = new Node(weight, element);
        }

        // Compare Huffman Tree based on their weights
        @Override
        public int compareTo(HuffmanTree o) {
            if(root.weight < o.root.weight) // in reverse order
                return 1;
            else if(root.weight == o.root.weight) // in reverse order
                return 0;
            else
                return -1;
        }

         static HuffmanTree getHuffmanTree(int[] counts) {
             // Create a heap to hold trees
            Heap<HuffmanTree> heap = new Heap<HuffmanTree>();
            for(int i = 0; i < counts.length; i++) {
                if(counts[i] > 0)
                    heap.add(new HuffmanTree(counts[i], (char) i)); // A leaf node tree
            }

            while(heap.getSize() > 1) {
                HuffmanTree t1 = heap.remove(); // Remove the smallest weight tree
                HuffmanTree t2 = heap.remove(); // Remove the next smallest weight
                heap.add(new HuffmanTree(t1, t2)); // Combine two trees
            }

            return heap.remove(); // The final tree
        }

         static void getHuffmanCode(HuffmanTree.Node root) {
            if(root == null)
                return;
             codes = new String[256];
            assignCode(root);
        }

         static void assignCode(HuffmanTree.Node root) {
            if(root.left != null) {
                root.left.code = root.code + "0";
                assignCode(root.left);

                root.right.code = root.code + "1";
                assignCode(root.right);
            }else
                codes[(int) root.element] = root.code;
        }

        // Inner class Node for storing each Node
        public class Node {
            // Attributes
            char element; // Stores the character for a leaf node
            int weight; // weight of the subtree rooted at this node
            public Node left;   // Reference to the left subtree
            public Node right;  // Reference to the right subtree
            String code = ""; // The code of this node from the root

            public Node() {
            }
            // Create a node with the specified weight and character
            public Node(int weight, char element) {
                this.weight = weight;
                this.element = element;
            }
        }
    }
}

// BitOutputStream from duy nguyen's assignment
class BitOutputStream {
    private FileOutputStream fileOutput;
    private int byteValue = 0; // value of byte
    private int count = 0; // iterate up to 8 bits
    private int mask = 1; // fills first bit of value with mask

    public BitOutputStream(File file, boolean append) throws IOException {
        fileOutput = new FileOutputStream(file, append);
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

// from C23 - duy
class Heap<E extends Comparable<E>> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

    /** Create a default heap */
    public Heap() {
    }

    /** Create a heap from an array of objects */
    public Heap(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Add a new object into the heap */
    public void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(
                    list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }
            else
                break; // the tree is a heap now

            currentIndex = parentIndex;
        }
    }

    /** Remove the root from the heap */
    public E remove() {
        if (list.size() == 0) return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(
                    list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }
            else
                break; // The tree is a heap
        }

        return removedObject;
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
        return list.size();
    }
}



