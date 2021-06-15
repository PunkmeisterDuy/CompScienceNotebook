package Review;

public class C11 {
    public static void main(String[] args) {
        Person guy = new Person();
        Child child = new Child();

    }
}

class Person {

    String name;
    Person() { // this is a constructor
        name = "hello";
    }
    Person(String name) {
        this.name = name;
    }

    void backflip() {
        System.out.println("backflip");
        System.out.println(name);
    }

}

class Child extends Person {
    Child() {
        //super(); //uses super classes constructor (the no arg constructor) the same without using super
        super(" " +
                " frank");
        backflip();
        // or
        // super.backflip();
    }

    void backflip() { //overrides inherited method
        System.out.println("unsafe backflip");
        System.out.println(name);
    }

    void backflip(boolean practiced) { //overrides inherited method
        System.out.println("backflip");
        System.out.println(name);
    }

}
