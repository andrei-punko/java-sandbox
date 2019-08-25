package by.andd3dfx.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Solution for https://www.hackerrank.com/challenges/the-time-in-words/problem
 */
public class TimeInWords {

    static String timeInWords(int h, int m) {
        String[] numbers = {
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen",
            "twenty",
            "twenty one",
            "twenty two",
            "twenty three",
            "twenty four",
            "twenty five",
            "twenty six",
            "twenty seven",
            "twenty eight",
            "twenty nine"
        };
        if (m == 0) {
            return numbers[h] + " o' clock";
        }
        if (m == 1) {
            return "one minute past " + numbers[h];
        }
        if (m == 15) {
            return "quarter past " + numbers[h];
        }
        if (m == 30) {
            return "half past " + numbers[h];
        }
        if (m == 45) {
            return "quarter to " + numbers[h + 1];
        }
        if (m < 30) {
            return numbers[m] + " minutes past " + numbers[h];
        }
        if (m > 30) {
            return numbers[60 - m] + " minutes to " + numbers[h + 1];
        }

        throw new IllegalArgumentException("Could not determine!");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = timeInWords(h, m);
        bufferedWriter.write(result);
        bufferedWriter.newLine();
        bufferedWriter.close();

        scanner.close();
    }
}
