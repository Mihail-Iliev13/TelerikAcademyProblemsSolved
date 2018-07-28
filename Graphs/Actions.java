import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Actions {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] input = bf.readLine().split(" ");
    int n = Integer.parseInt(input[0]);
    int m = Integer.parseInt(input[1]);

    HashMap<Integer, List<Integer>> incomingEdges = new HashMap<>();

    for (int i = 0; i < n; i++) {
      incomingEdges.put(i, new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {

      String[] pair = bf.readLine().split(" ");
      int a = Integer.parseInt(pair[0]);
      int b = Integer.parseInt(pair[1]);
      incomingEdges.get(b).add(a);
    }

    StringBuilder result = new StringBuilder();
    order(result, incomingEdges);
    System.out.println(result);

  }

  private static void order (StringBuilder result, HashMap<Integer, List<Integer>> incoming) {

    if (incoming.isEmpty()) {
      return;
    }

    for (Integer key : incoming.keySet()) {

      if (!incoming.get(key).isEmpty()) {
        continue;
      }

      result.append(key);
      result.append("\n");
      incoming.remove(key);
      clear(incoming, key);
      order(result, incoming);
      break;

    }
  }

  private static void clear(HashMap<Integer, List<Integer>> incoming, Integer key) {
    for (Integer i : incoming.keySet()) {
      if (incoming.get(i).contains(key)) {
        incoming.get(i).remove(key);
      }
    }
  }
}