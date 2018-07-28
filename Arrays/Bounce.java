import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Bounce {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] input = bf.readLine().split(" ");
    int rows = Integer.parseInt(input[0]);
    int cols = Integer.parseInt(input[1]);

    long sum = 0;
    if(rows % 2 == 1) {
      sum = sum + calculateOdd(rows,cols);
    }else {
      sum = sum + calculateEven(rows, cols);
    }
    System.out.println(sum);
  }

  // calculates sum for matrix with odd number of rows
  private static long calculateOdd(int rows, int cols) {
    long sum = 0;
    long start = 1;
    long current = 1;
    long last = 0;


    for (int row = 0; row < rows; row++) {
      if(row == 1) {
        start = start * 4;
      }
      current = start;
      for (int col = 0; col < cols/2; col++) {
        if (row == 0 || row == rows -1) {
          sum = sum + current;
          current = current * 4 ;
        }else {
          sum = sum + (current * 2);
          last = current;
          current = current * 4;
        }
      }
      if (row % 2 == 1 && row != rows - 1) {
        sum = sum - last;
      }else if(row != 0 && row != rows - 1){
        sum = sum - start;
        start = start * 4;
      }
    }
    return sum;
  }

  // calculate sum for matrix with even number of rows
  private static long calculateEven(int rows, int cols) {
    long sum = 0;
    long start = 1;
    long current = 1;
    long last = 0;
    for (int row = 0; row < rows; row++) {
      if(row == 1) {
        start = start * 4;
      };
      current = start;

      if(row % 2 == 0) {
        for (int col = 0; col < (cols/2) + 1; col++) {
          if(row == 0 || row == rows-1) {
            sum = sum + current;
            current = current * 4;
          } else {
            sum =  sum + (current * 2);
            last = current;
            current = current * 4;
          }
        }


        if (row != 0 && row != rows-1) {
          sum = sum - (start + last);
          start = start * 4;
        }
      } else {
        for (int col = 0; col < cols/2; col++) {
          if(row == rows-1) {
            sum = sum + current;
            current = current * 4;
          }else {
            sum = sum + (current * 2);
            current = current * 4;
          }
        }
      }
    }
    return sum;
  }
}