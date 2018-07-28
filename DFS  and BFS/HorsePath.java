import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HorsePath {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int size = Integer.parseInt(bf.readLine());
    int[][] matrix = new int[size][size];
    int[] rows = {-2, -2, -1, -1, +1, +1, +2, +2};
    int[] cols = {-1, +1, -2, +2, -2, +2, -1, +1};
    int number = 1;
    int maxPossibleNumber = size*size;
    int possibleMovements = rows.length;
    matrix[0][0] = 1;
    int currRow = 0, currCol = 0;

    while (number < maxPossibleNumber) {
      ++number;
      int indexOfNextMove = 0;
      indexOfNextMove = moveHorse(indexOfNextMove, rows, cols, matrix, currRow, currCol);

      if (indexOfNextMove >= possibleMovements) {
        String[] rowAndCol = getRowAndCol(matrix);
        currRow = Integer.parseInt(rowAndCol[0]);
        currCol = Integer.parseInt(rowAndCol[1]);
        matrix[currRow][currCol] = number;
      } else {
        currRow += rows[indexOfNextMove];
        currCol += cols[indexOfNextMove];
        matrix[currRow][currCol] = number;
      }
    }
    printMatrix(matrix);
  }

  private static int moveHorse(int index, int[] rows, int[] cols, int[][] grid, int currRow, int currCol) {

    if (isValid(currRow + rows[index] , currCol + cols[index], grid.length, grid)
            && grid[currRow + rows[index]][currCol + cols[index]] == 0) { ;
      return index;
    }
    ++index;
    if (index >= rows.length) {
      return index;
    }
    return moveHorse(index, rows, cols, grid, currRow , currCol);
  }

  private static String[] getRowAndCol(int[][] matrix) {
    String[]res = new String[2];
    for (int row = 0; row < matrix[0].length; row++) {
      for (int col = 0; col < matrix.length; col++) {
        if (matrix[row][col] == 0) {
          res[0] = String.valueOf(row);
          res[1] = String.valueOf(col);
          return res;
        }
      }
    }
    return res;
  }

  private static boolean isValid(int row, int col, int size, int[][] matrix) {
    return (row >= 0 && row < size && col >= 0 && col < size && matrix[row][col] == 0);
  }

  private static void printMatrix (int[][] matrix){
    for (int[] arr: matrix) {
      for (int el: arr) {
        System.out.print(el + " ");
      }
      System.out.println();
    }
  }
}