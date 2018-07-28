import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class RedRidingHood {

  public static int maxCoins = 0;
  public static int bestVertex;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(bf.readLine());
    String[] inputValuesStr = bf.readLine().split(" ");
    int[] inputValues = new int[inputValuesStr.length];

    for (int i = 0; i < inputValues.length; i++) {
      inputValues[i] = Integer.parseInt(inputValuesStr[i]);
    }

    HashMap<Integer, Integer> values = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> neighbours = new HashMap<>();

    for (int i = 1; i <= n; i++) {
      values.put(i, inputValues[i - 1]);

      if (i == n) {
        continue;
      }

      String[] inputEdge = bf.readLine().split(" ");
      int a = Integer.parseInt(inputEdge[0]);
      int b = Integer.parseInt(inputEdge[1]);
      addEdge(a, b, neighbours);
      addEdge(b, a, neighbours);
    }

    dfs(1, 0,0, neighbours, values);
    maxCoins = 0;
    dfs(bestVertex, 0, 0, neighbours, values);
    System.out.println(maxCoins);

  }

  private static void addEdge(int vertixA, int vertixB, Map<Integer, ArrayList<Integer>> neighbours) {

    if (!neighbours.containsKey(vertixA)) {
      neighbours.put(vertixA, new ArrayList<Integer>());
    }

    neighbours.get(vertixA).add(vertixB);
  }


  private static void dfs(int vertex, int prev, int currentCoins, HashMap<Integer, ArrayList<Integer>> neigbours,
                          HashMap<Integer, Integer> values) {
    currentCoins += values.get(vertex);
    boolean hasNext = false;

    for (int neigbour : neigbours.get(vertex)) {
      if (neigbour == prev) {
        continue;
      }
      hasNext = true;
      dfs(neigbour, vertex, currentCoins, neigbours, values);

    }

    if (!hasNext) {

      if (currentCoins > maxCoins) {
        maxCoins  = currentCoins;
        bestVertex = vertex;
      }
    }
  }
}