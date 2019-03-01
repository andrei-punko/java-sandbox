package by.andd3dfx.sorting;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractSort {
    protected Long[] items;
    protected int elementsCount;

    public AbstractSort(int maxSize) {
        items = new Long[maxSize];
        elementsCount = 0;
    }

    public void insert(long item) {
        items[elementsCount] = item;
        elementsCount++;
    }

    public void insert(List<Long> items) {
        this.items = items.toArray(new Long[0]);
        elementsCount = items.size();
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

    public Long[] getItems() {
        return Arrays.copyOf(items, elementsCount);
    }

    public int getElementsCount() {
        return elementsCount;
    }
}
