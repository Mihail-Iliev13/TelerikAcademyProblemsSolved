import java.io.*;
import java.util.LinkedList;

public class CombinationsWithRepetitions {


  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] input = bf.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int K = Integer.parseInt(input[1]);
    generateCombinations(new LinkedList(), 1, N, K, 1);
  }



  private static void generateCombinations(LinkedList<Integer> list, int index, int N, int K, int start) {
    if (list.size() == K) {
      System.out.println((listToString(list)));
      return;
    }

    for (int i = start; i <= N; i++) {

      list.add(i);
      start = i;
      generateCombinations(list, index + 1, N, K , start);
      list.removeLast();
    }

  }

  private static String listToString(LinkedList<Integer> list) {
    StringBuilder sb = new StringBuilder();
    for (Integer aList : list) {
      sb.append(aList).append(' ');
    }
    return sb.toString();
  }

}