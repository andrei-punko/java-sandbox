package by.andd3dfx.interview.ya;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/*
Дан класс:
class Foot implements Runnable {
	private String name;

	public Foot(String name) {
		this.name = name;
	}

	public void run() {
		for(;;) {
			step();
		}
	}

	private void step() {
		System.out.println(name + " steps!");
	}
}

И программа:
public class MainClass {
    public static void main(String[] args) {
        new Thread(new Foot("left")).start();
        new Thread(new Foot("right")).start();

        while(true);
    }
}

Исправить программу, чтобы робот шагал ногами по очереди.
Сделать так, чтобы не потреблялись ресурсы CPU, пока ожидаем передвижения очередной ноги.
*/
public class TwoLegsRobot {
    static class Foot implements Runnable {
        private String name;
        private volatile static AtomicInteger counter = new AtomicInteger(1);

        public Foot(String name) {
            this.name = name;
        }

        public void run() {
            for (; ; ) {
                step();
            }
        }

        private void step() {
            synchronized (counter) {
                if (counter.get() == 1 && "left".equals(name)) {
                    counter.set(-1);
                    System.out.println(name + " steps!");
                    return;
                }

                if (counter.get() == -1 && "right".equals(name)) {
                    counter.set(1);
                    System.out.println(name + " steps!");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Foot("left")).start();
        new Thread(new Foot("right")).start();

        sleep(1);
    }
}
