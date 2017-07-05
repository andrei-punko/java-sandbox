package by.andd3dfx.sorting;

import java.util.Arrays;

public abstract class AbstractSort {
    protected long items[];
    protected int elementsCount;

    public AbstractSort(int maxSize) {
        items = new long[maxSize];
        elementsCount = 0;
    }

    public void insert(long item) {
        items[elementsCount] = item;
        elementsCount++;
    }

    public abstract void sort();

    protected void swap(int in_index, int out_index) {
        long temp = items[in_index];
        items[in_index] = items[out_index];
        items[out_index] = temp;
    }

    public void display() {
        for (int i = 0; i < elementsCount; i++) {
            System.out.println(items[i]);
        }
        System.out.printf("");
    }

    public long[] getItems() {
        return Arrays.copyOf(items, elementsCount);
    }

    public int getElementsCount() {
        return elementsCount;
    }
}
