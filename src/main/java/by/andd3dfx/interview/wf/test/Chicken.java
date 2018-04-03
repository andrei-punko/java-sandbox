package by.andd3dfx.interview.wf.test;

import java.util.concurrent.Callable;

interface IBird {

  Egg lay();
}

public class Chicken {

  public Chicken() {
  }

  public static void main(String[] args) {
    Chicken chicken = new Chicken();
    System.out.println(chicken instanceof IBird);
  }
}

class Egg {

  public Egg(Callable<IBird> createBird) {
    throw new UnsupportedOperationException();
  }

  public IBird hatch() {
    throw new UnsupportedOperationException();
  }
}
