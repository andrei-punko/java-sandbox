package by.andd3dfx.sorting;

import java.util.Arrays;

public class BubbleSort {

    private long items[];
    private int elementsCount;

    public BubbleSort(int maxSize) {
        items = new long[maxSize];
        elementsCount = 0;
    }

    public void insert(long item) {
        items[elementsCount] = item;
        elementsCount++;
    }

    public void sort() {
        for (int out_index = elementsCount - 1; out_index > 0; out_index--) {
            for (int in_index = 0; in_index < out_index; in_index++) {
                if (items[out_index] < items[in_index]) swap(items, in_index, out_index);
            }
        }
    }

    private void swap(long[] items, int in_index, int out_index) {
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
