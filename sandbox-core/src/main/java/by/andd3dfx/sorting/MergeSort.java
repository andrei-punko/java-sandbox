package by.andd3dfx.sorting;

public class MergeSort extends AbstractSort {

    @Override
    public void sort() {
        mergeSort(items, items.length);
    }

    public void mergeSort(Long[] items, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Long[] l = new Long[mid];
        Long[] r = new Long[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = items[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = items[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(items, l, r, mid, n - mid);
    }

    public void merge(Long[] items, Long[] l, Long[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                items[k] = l[i];
                k++;
                i++;
            } else {
                items[k] = r[j];
                k++;
                j++;
            }
        }
        while (i < left) {
            items[k] = l[i];
            k++;
            i++;
        }
        while (j < right) {
            items[k] = r[j];
            k++;
            j++;
        }
    }
}
