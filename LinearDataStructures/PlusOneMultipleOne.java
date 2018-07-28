import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PlusOneMultipleOne {

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int[] values = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int N = values[0];
    int M = values[1];
    LinkedList<Integer> numbers = new LinkedList<>();
    numbers.add(N);
    int members = 1;
    int current = numbers.removeFirst();
    while (true){
      if (members == M){
        System.out.println(current);
        break;
      }
      numbers.add(current + 1);
      numbers.add(2 * current + 1);
      numbers.add(current + 2);
      current = numbers.removeFirst();
      members++;
    }
  }

  private static void compareWithM(int i, int M, LinkedList<Integer> numbers) {
    if(i == M){
      System.out.println(numbers.getLast());
      System.exit(0);
    }
  }
}