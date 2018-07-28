import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;

public class SubsetOfSumS {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int s = Integer.parseInt(reader.readLine());

    int[] numbers = Arrays.stream(stringArrToInt(reader.readLine()
            .split(" ")))
            .sorted().toArray();


    int[] sums = new int[s + 1];
    for (int i = 0; i <= s; i++) {
      sums[i] = i;
    }

    boolean[][] matrix = new boolean[numbers.length][sums.length];
    matrix[0][0] = true;

    for (int row = 1; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        if (matrix[row - 1][col]) {
          matrix[row][col] = true;
        } else {
          int difference = sums[col] - numbers[row];
          if (difference >= 0 && matrix[row - 1][difference]) {
            matrix[row][col] = true;
          } else if (numbers[row] == sums[col]) {
            matrix[row][col] = true;
          } else {
            for (int availableSet = row - 1; availableSet >= 0; availableSet--) {
              if (difference == numbers[availableSet]) {
                matrix[row][col] = true;
              }
            }
          }
        }
      }
    }

    if (matrix[numbers.length - 1][sums.length - 1]) {
      System.out.println("yes");
    } else {
      System.out.println("no");
    }
  }

  private static int[] stringArrToInt(String[] strArr) {
    int[] array = new int[strArr.length];
    for (int i = 0; i <array.length ; i++) {
      array[i] = Integer.parseInt(strArr[i]);
    }
    return  array;
  }
}