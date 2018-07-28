import java.math.BigInteger;
import java.util.Scanner;

public class BitShiftMatrix {

  public static void main(String[] args) {

    Scanner in = new Scanner (System.in);
    int rows = in.nextInt();
    int cols = in.nextInt();
    int coeff = Math.max(rows,cols);
    int moves = in.nextInt();
    in.nextLine();
    BigInteger sum = BigInteger.ZERO;
    String input = in.nextLine();
    String[] codesString = input.split(" ");
    int[] codes = new int[codesString.length];
    for(int i = 0;i<codes.length;i++){
      codes[i] = Integer.parseInt(codesString[i]);
    }
    BigInteger[][]matrix = new BigInteger[rows][cols];
    boolean[][] isEmpty = new boolean[rows][cols];
    matrix[rows-1][0] = BigInteger.ONE;
    for(int col = 1; col<cols;col++){
      matrix[rows-1][col] = matrix[rows-1][col-1].multiply(BigInteger.valueOf(2));
    }

    for(int col = 0;col<cols;col++){
      for(int row = matrix.length-2;row>=0;row--){
        matrix[row][col] = matrix[row+1][col].multiply(BigInteger.valueOf(2));
      }
    }


    int currRow = rows-1;
    int currCol = 0;

    for(int move = 0; move<moves;move++){
      int newRow = codes[move]/coeff; // 2
      int newCol = codes[move] % coeff; // 2

      if(currRow<newRow && currCol<newCol){
        for(int col = currCol; col<=newCol;col++){
          if(!isEmpty[currRow][col]){
            sum = sum.add(matrix[currRow][col]);
            isEmpty[currRow][col] = true;
          }
        }
        for(int row = currRow;row<=newRow;row++){
          if(!isEmpty[row][newCol]){
            sum = sum.add(matrix[row][newCol]);
            isEmpty[row][newCol] = true;
          }            }
      }

      if(currRow>newRow && currCol<newCol){
        for(int col = currCol; col<=newCol;col++){
          if(!isEmpty[currRow][col]){
            sum = sum.add(matrix[currRow][col]);
            isEmpty[currRow][col] = true;
          }
        }
        for(int row = currRow;row>=newRow;row--){
          if(!isEmpty[row][newCol]){
            sum = sum.add(matrix[row][newCol]);
            isEmpty[row][newCol] = true;
          }
        }
      }

      if(currRow<newRow && currCol>newCol){
        for(int col = currCol; col>=newCol;col--){
          if(!isEmpty[currRow][col]){
            sum = sum.add(matrix[currRow][col]);
            isEmpty[currRow][col] = true;
          }
        }
        for(int row = currRow;row<=newRow;row++){
          if(!isEmpty[row][newCol]){
            sum = sum.add(matrix[row][newCol]);
            isEmpty[row][newCol] = true;
          }
        }
      }

      if(currRow>newRow && currCol>newCol){
        for(int col = currCol; col>=newCol;col--){
          if(!isEmpty[currRow][col]){
            sum = sum.add(matrix[currRow][col]);
            isEmpty[currRow][col] = true;
          }
        }
        for(int row = currRow;row>=newRow;row--){
          if(!isEmpty[row][newCol]){
            sum = sum.add(matrix[row][newCol]);
            isEmpty[row][newCol] = true;
          }
        }
      }

      if(currRow>newRow && currCol == newCol){
        for(int row = currRow;row>=newRow;row--){
          if(!isEmpty[row][newCol]){
            sum = sum.add(matrix[row][newCol]);
            isEmpty[row][newCol] = true;
          }
        }
      }

      if(currRow<newRow && currCol == newCol){
        for(int row = currRow;row<=newRow;row++){
          if(!isEmpty[row][newCol]){
            sum = sum.add(matrix[row][newCol]);
            isEmpty[row][newCol] = true;
          }
        }
      }

      if(currRow == newRow && currCol>newCol){
        for(int col = currCol; col>=newCol;col--){
          if(!isEmpty[currRow][col]){
            sum = sum.add(matrix[currRow][col]);
            isEmpty[currRow][col] = true;
          }
        }
      }

      if(currRow == newRow && currCol<newCol){
        for(int col = currCol; col<=newCol;col++){
          if(!isEmpty[currRow][col]){
            sum = sum.add(matrix[currRow][col]);
            isEmpty[currRow][col] = true;
          }
        }
      }



      currRow = newRow;
      currCol = newCol;
    }
    System.out.println(sum);
  }
}