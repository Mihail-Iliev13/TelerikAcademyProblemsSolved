import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class CokiSkoki {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(bf.readLine());
    int[] buildings = Arrays.stream(bf.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();


    int[] results = new int[buildings.length];
    Stack<Integer> stack = new Stack<>();
    stack.push(buildings[buildings.length - 1]);
    int maxJumps = 0;

    for (int i = buildings.length - 2; i >= 0; i--) {
      int current = buildings[i];

      while (!stack.isEmpty()) {
        if (current < stack.peek()) {
          results[i] = stack.size();
          break;
        } else {
          stack.pop();
        }
      }
      stack.push(current);
      if (results[i] > maxJumps) {
        maxJumps = results[i];
      }
    }

    System.out.println(maxJumps);
    for (int el : results) {
      System.out.print(el + " ");
    }
  }
}