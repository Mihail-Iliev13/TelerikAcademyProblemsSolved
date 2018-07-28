import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LargestAreaInMatrix {

  public static int counter = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] input = bf.readLine().split(" ");
    int n = Integer.parseInt(input[0]);
    int m = Integer.parseInt(input[1]);


    int[][] matrix = fillMatrix(bf, n, m);


    boolean isChecked[][] =  new boolean[n][m];
    int maxSequence = 1;

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {

        if (isChecked[row][col]) {
          continue;
        }

        int value = matrix[row][col];
        countSequence(row, col,  value, matrix, isChecked);
        maxSequence = Math.max(counter, maxSequence);
        counter = 0;
      }
    }

    System.out.println(maxSequence);

  }

  private static int[][] fillMatrix(BufferedReader bf, int n, int m) throws IOException {
    int[][] matrix = new int[n][m];

    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = Arrays.stream(bf.readLine().split(" "))
              .mapToInt(Integer::parseInt)
              .toArray();
    }
    return matrix;
  }

  private static void countSequence(int row, int col, int value, int[][] matrix, boolean[][] isChecked) {

    if (!isValid(row, col, matrix)
            || matrix[row][col] != value
            || isChecked[row][col]) {
      return;
    }

    counter++;
    isChecked[row][col] = true;
    countSequence( row, col + 1, value, matrix, isChecked);
    countSequence(row, col - 1, value, matrix, isChecked);
    countSequence(row - 1, col, value, matrix, isChecked);
    countSequence(row + 1, col, value, matrix, isChecked);
  }

  static boolean isValid(int row, int col, int[][] matrix) {
    return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
  }
}