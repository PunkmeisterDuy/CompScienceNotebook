package Review;

public class C10 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

class City { //
    private Tree tree;
    private Car car;

    // when city is destroyed, tree and car are not destroyed
}
class Tree {}

class Car { // Composition (a special case of aggregation)
    private CarParts carparts;
    // when car is destroyed, carParts are destroyed
}

class CarParts {}
