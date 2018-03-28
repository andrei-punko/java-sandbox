package by.andd3dfx.interview.workfusion.test;

/*
You are given two paper strips. On each strip, numbers (1,2, ... N) are written in random order. Cut the original paper
strip into several pieces and rearrange those pieces to form the desired sequence.

Write a function that returns the minimum number of cut pieces needed to perform the described operation.

For example, the following code should display 3 because the pieces used should be (1), (4,3), and (2):
int[] original = new int[] { 1, 4, 3, 2 };
int[] desired = new int[] { 1, 2, 4, 3 };
System.out.println(PaperStrip.minPieces(original, desired));
 */
public class PaperStrip {

  public static boolean minPieces(int[] original, int[] desired) {
    throw new UnsupportedOperationException("Waiting to be implemented.");
  }

  public static void main(String[] args) {
    int[] original = new int[]{1, 4, 3, 2};
    int[] desired = new int[]{1, 2, 4, 3};
    System.out.println(PaperStrip.minPieces(original, desired));
  }
}
