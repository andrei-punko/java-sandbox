package creational.abstractfactory;

/**
 * Created by Andrei_Punko on 2/5/2017.
 */
public class ProductB1 implements AbstractProductB {

    @Override
    public void interact(AbstractProductA a) {
        System.out.println(this.getClass().getName() + " interacts with " + a.getClass().getName());
    }
}
