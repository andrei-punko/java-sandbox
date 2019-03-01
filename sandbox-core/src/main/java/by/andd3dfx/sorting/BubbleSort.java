package by.andd3dfx.sorting;

public class BubbleSort extends AbstractSort {

    public BubbleSort(int maxSize) {
        super(maxSize);
    }

    @Override
    public void sort() {
        for (int out_index = elementsCount - 1; out_index > 0; out_index--) {
            for (int in_index = 0; in_index < out_index; in_index++) {
                if (items[in_index + 1] < items[in_index]) {
                    swap(in_index, in_index + 1);
                }
            }
        }
    }
}
