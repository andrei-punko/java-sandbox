package by.andd3dfx.multithreading;

/*
We have 2 interfaces - EventHandler & IPool:

public interface EventHandler {
	void handleEvent(Event event);
}

public interface IPool {
	void start();
	void stop();
	void submit(Event event);
	void setEventHandler(EventHandler eventHandler);
}

Write IPool implementation

Sample of pool usage:
  IPool pool = new CustomPool();
  pool.setEventHandler(someEventHandler);
  pool.start();
  pool.submit(event);   - multiple times
  ...
  pool.stop();
 */
public class CustomPool {

}
