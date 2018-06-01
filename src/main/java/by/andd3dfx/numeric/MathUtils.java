package by.andd3dfx.numeric;

public class MathUtils {

    /*
    Write method to reverse order of digits in Integer number
    */
    public static int reverse(int number) {
        int result = 0;
        do {
            int digit = number % 10;
            result = result * 10 + digit;
            number /= 10;
        } while (number > 0);

        return result;
    }

    /*
    Implement binary search. Return element index or -1 if it's not exist
     */
    public static int binarySearch(int[] array, int element) {
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
