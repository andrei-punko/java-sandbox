package by.andd3dfx.multithreading;

/*
TODO: write custom implementation of ThreadPoolExecutor

From jdk documentation:
Thread pools address two different problems: they usually provide improved performance when executing large numbers of
asynchronous tasks, due to reduced per-task invocation overhead, and they provide a means of bounding and managing the
resources, including threads, consumed when executing a collection of tasks. Each ThreadPoolExecutor also maintains
some basic statistics, such as the number of completed tasks.
*/
public class CustomThreadPoolExecutor {

    public void submit(Runnable task) {
        // ...
    }

    public void shutdown() {
        // ...
    }
}
