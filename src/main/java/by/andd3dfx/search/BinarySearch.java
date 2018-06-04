package by.andd3dfx.search;

/*
 Implement binary search. Return element index or -1 if it's not exist
 */
public class BinarySearch {

  public static int perform(int[] array, int element) {
    int left = 0;
    int right = array.length;

    do {
      int middle = (left + right) / 2;
      if (array[middle] == element) {
        return middle;
      }
      if (array[middle] < element) {
        left = middle;
      } else {
        right = middle;
      }
    } while (right - left > 1);

    return -1;
  }
}
