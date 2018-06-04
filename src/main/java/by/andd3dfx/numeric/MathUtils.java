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
}
