import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Tubes {

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int tubes = Integer.parseInt(in.readLine());
    int tubesNeeded = Integer.parseInt(in.readLine());
    int[] tubesLen = new int[tubes];
    for (int i = 0; i < tubesLen.length; i++) {
      tubesLen[i] = Integer.parseInt(in.readLine());
    }

    int maxLenght = Arrays.stream(tubesLen).max().getAsInt();
    int left = 1;
    int right = maxLenght;
    System.out.println(findMaxLen(maxLenght, left, right, tubesLen, tubesNeeded));
  }

  private static int findMaxLen(int maxLenght, int left, int right, int[] tubesLen, int tubesNeeded) {

    if (left > right) {
      return maxLenght;
    }

    int mid = (right + left)/2;

    if(maxLenght < 1) {
      System.out.println("-1");
      System.exit(0);;
    }


    int counter = 0;

    for (int tube : tubesLen) {
      counter += tube / mid;
    }

    if (counter < tubesNeeded) {
      return findMaxLen(maxLenght, left, mid - 1, tubesLen, tubesNeeded);
    }

    return findMaxLen(mid, mid + 1, right, tubesLen, tubesNeeded);
  }
}