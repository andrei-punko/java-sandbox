package by.andd3dfx.multithreading.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implement custom stack with multithreading support without using blocking operations
 */
public class CustomStack<T> {

    private Deque<T> deque = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();   // TODO: switch to ReadWriteLock

    public T push(T element) {
        lock.lock();
        try {
            deque.push(element);
            return element;
        } finally {
            lock.unlock();
        }
    }

    public T pop() {
        lock.lock();
        try {
            return deque.pop();
        } finally {
            lock.unlock();
        }
    }

    public T peek() {
        lock.lock();
        try {
            return deque.peek();
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return deque.isEmpty();
        } finally {
            lock.unlock();
        }
    }
}
