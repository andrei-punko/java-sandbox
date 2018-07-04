package by.andd3dfx.multithreading.threadpool;

/*
    Write custom implementation of ThreadPoolExecutor that support next 2 methods: submit(Runnable) and shutdown()

    Solution based on http://www.makeinjava.com/custom-thread-pool-example-without-using-executor-framework/

    1.ThreadPool class creates numbers of TaskExecutor instances.
        - TaskExecutor class will be responsible for executing the tasks
    2.ThreadPool class exposes one method submitTask.
        - submitTask method will be called by task generating program, to submit a task to threadPool.
 */
public class ThreadPool {

    private BlockingQueue<Runnable> queue;
    private volatile boolean shutdownState = false;

    public ThreadPool(int queueSize, int threadsCount) {
        queue = new BlockingQueue<>(queueSize);

        for (int threadNumber = 0; threadNumber < threadsCount; threadNumber++) {
            String threadName = "Thread-" + threadNumber;
            TaskExecutor task = new TaskExecutor(queue);
            Thread thread = new Thread(task, threadName);
            thread.start();
        }
    }

    public void submitTask(Runnable task) throws InterruptedException {
        if (!shutdownState) {
            queue.enqueue(task);
        } else {
            System.out.println("Task rejected because of shutdown state");
        }
    }

    /**
     * In shutdown state Thread Pool stops accepting new tasks
     */
    public void shutdown() {
        shutdownState = true;
    }

    public boolean isShutdownState() {
        return shutdownState;
    }
}
