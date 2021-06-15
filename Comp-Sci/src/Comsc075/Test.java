package Comsc075;

class Beeg {

    Beeg() {
        System.out.println("hi");
    }

    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Divisor is 0");
        }
        return num1/num2;
    }

}



public class Test {
    public static void main(String[] args) throws ArithmeticException {

        try {
            Beeg.divide(3, 0);
        } catch (Exception ex) {
            System.out.println("ur bad");
        }

    }

}
