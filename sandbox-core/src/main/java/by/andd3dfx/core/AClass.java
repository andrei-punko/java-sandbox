package by.andd3dfx.core;

public class AClass {

    private int value = 100;

    {
        System.out.println("Usual block of class A, " + value);
    }

    static {
        System.out.println("Static block of class A");
    }

    public AClass() {
        System.out.println("Class A constructor, " + value);
        showValue();
    }

    public void showValue() {
        System.out.println("Call of class A method, " + value);
    }
}
