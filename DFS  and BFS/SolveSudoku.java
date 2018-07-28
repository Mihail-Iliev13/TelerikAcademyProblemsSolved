import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolveSudoku {

  private static int[][] sudoku;

  public static void main(String[] args) throws IOException {
    sudoku = fillSudoku(new int[9][9]);
    solve(0,0);
  }

  private static void solve(int row, int col) {

    if (row == sudoku.length ){
      printSudoku();
      System.exit(0);
    } else if (sudoku[row][col] == 0) {

      for (int i = 1; i <= 9; i++) {

        if (squareClear(row, col, i) && rowClear(col, i) && colClear(row, i)) {


          sudoku[row][col] = i;

          int nextRow;
          int nextCol;
          if (col + 1 > sudoku.length - 1) {
            nextCol = 0;
            nextRow = row + 1;
          } else {
            nextCol = col + 1;
            nextRow = row;
          }

          solve(nextRow, nextCol);
          sudoku[row][col] = 0;

        }
      }
    } else {

      int nextRow;
      int nextCol;
      if (col + 1 > sudoku.length - 1) {
        nextCol = 0;
        nextRow = row + 1;
      } else {
        nextCol = col + 1;
        nextRow = row;
      }

      solve(nextRow, nextCol);
    }

  }

  private static boolean squareClear(int row, int col, int i) {
    int startRow;
    int endRow;

    if (row <= 2) {
      startRow = 0;
      endRow = 2;
    } else if (row <= 5) {
      startRow = 3;
      endRow = 5;
    } else {
      startRow = 6;
      endRow = 8;
    }

    int startCol;
    int endCol;

    if (col <= 2) {
      startCol = 0;
      endCol = 2;
    } else if (col <= 5) {
      startCol = 3;
      endCol = 5;
    } else {
      startCol = 6;
      endCol = 8;
    }


    for (int currRow = startRow; currRow <= endRow ; currRow++) {
      for (int currCol = startCol; currCol <= endCol; currCol++) {

        if (sudoku[currRow][currCol] == i) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean rowClear(int col, int i) {
    for (int j = 0; j < sudoku.length; j++) {

      if(sudoku[j][col] == i) {
        return false;
      }
    }
    return true;
  }

  private static boolean colClear(int row, int i) {
    for (int j = 0; j < sudoku.length; j++) {

      if(sudoku[row][j] == i) {
        return false;
      }
    }
    return true;
  }

  private static int[][] fillSudoku(int[][] sudoku) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    for (int row = 0; row < sudoku.length; row++) {

      String[] input = in.readLine().split("");

      for (int col = 0; col < sudoku[row].length; col++) {
        if (input[col].equals("-")) {
          sudoku[row][col] = 0;
        } else {
          sudoku[row][col] = Integer.parseInt(input[col]);
        }
      }
    }
    return sudoku;
  }

  private static void printSudoku() {
    for (int[] arr : sudoku) {
      for (int el : arr) {
        System.out.print(el);
      }
      System.out.println("");
    }
  }
}