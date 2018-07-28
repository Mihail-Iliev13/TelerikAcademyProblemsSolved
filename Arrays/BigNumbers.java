import java.util.Scanner;

public class BigNumbers {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String uselessInput = in.nextLine();
    String[] inputFirst = in.nextLine().split(" ");
    String[] inputSecond  = in.nextLine().split(" ");
    int[] longestArr;
    int[] shortestArr;

    if (inputFirst.length > inputSecond.length) {
      longestArr = fillArray(inputFirst);
      shortestArr = fillArray(inputSecond);
    } else {
      longestArr = fillArray(inputSecond);
      shortestArr = fillArray(inputFirst);
    }

    StringBuilder output = new StringBuilder();
    int remainder = 0;
    int index = 0;


    while (index < longestArr.length) {
      int res  = 0;

      if (index < shortestArr.length) {
        res = longestArr[index] + shortestArr[index] + remainder;
      } else {
        res = longestArr[index] + remainder;
      }

      if (res > 9) {
        res -= 10;
        remainder = 1;
      } else {
        remainder = 0;
      }

      output.append(res).append(" ");
      index++;
    }

    System.out.println(output);
  }

  private static int[] fillArray(String[] strArr) {
    int[] intArr = new int[strArr.length];
    for (int i = 0; i < intArr.length ; i++) {
      intArr[i] = Integer.parseInt(strArr[i]);
    }
    return intArr;
  }

}