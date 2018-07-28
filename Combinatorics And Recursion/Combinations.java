import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Combinations {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] input = bf.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int K = Integer.parseInt(input[1]);
    generateCombinations(0,  N, 1, new int[K]);
  }



  private static void generateCombinations(int index, int N, int value, int[] current) {
    if (index == current.length) {
      System.out.println(arrToString(current));
      return;
    }

    for (int i = value; i <= N; i++) {
      current[index] = i;
      generateCombinations(index + 1,  N, i + 1, current );
    }

  }

  private static String arrToString(int[] nums) {
    StringBuilder sb = new StringBuilder();
    for (int num : nums) {
      sb.append(num).append(' ');
    }

    return sb.toString();
  }
}