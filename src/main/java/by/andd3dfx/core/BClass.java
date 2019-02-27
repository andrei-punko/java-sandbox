package by.andd3dfx.core;

/**
 * Describe what will be printed into log after BClass creation and call showValue() method on it
 */
public class BClass extends AClass {

    private int value = 1000;

    {
        System.out.println("Usual block of class B, " + value);
    }

    static {
        System.out.println("Static block of class B");
    }

    public BClass() {
        System.out.println("Class B constructor, " + value);
        showValue();
    }

    @Override
    public void showValue() {
        System.out.println("Call of class B method, " + value);
    }
}
