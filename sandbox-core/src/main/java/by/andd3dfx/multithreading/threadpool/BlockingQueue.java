package by.andd3dfx.multithreading.threadpool;

import java.util.LinkedList;
import java.util.Queue;

/*
    1.BlockingQueue simulates the blocking queue implementation.
    2.We have used LinkedList as underlying data structure.
    3.BlockingQueue contains couple of synchronized methods
        - enqueue : It enqueue (push) Task to the queue
        - dequeue : This method takes (pop) the task from the queue.
 */
public class BlockingQueue<Type> {

    private final int MAX_QUEUE_SIZE;
    private Queue<Type> queue = new LinkedList<>();

    public BlockingQueue(int size) {
        this.MAX_QUEUE_SIZE = size;
    }

    public synchronized void enqueue(Type task) throws InterruptedException {
        while (queue.size() == MAX_QUEUE_SIZE) {
            wait();
        }
        if (queue.isEmpty()) {
            notifyAll();
        }
        queue.offer(task);
    }

    public synchronized Type dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        if (queue.size() == MAX_QUEUE_SIZE) {
            notifyAll();
        }
        return queue.poll();
    }
}
