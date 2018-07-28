import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FriendsInNeed {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(bf.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

    int edges = input[1];
    int hospital = input[2];

    HashMap<Integer, List<Vertex>> vertices = new HashMap<>();
    HashSet<Integer> hospitals = Arrays.stream(bf.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .boxed().collect(Collectors.toCollection(HashSet::new));


    for (int i = 0; i < edges; i++) {

      int[] currentInput = Arrays.stream(bf.readLine().split(" "))
              .mapToInt(Integer::parseInt)
              .toArray();

      addEdge(currentInput, vertices, hospitals);
    }

    ArrayList<Integer> hospitalsList = new ArrayList<>(hospitals);
    int minDistance = Integer.MAX_VALUE;

    for (int hosp : hospitalsList) {
      HashMap<Integer, Integer> distances = dijkstra(hosp, vertices);
      int countDistances = 0;

      for (Integer key : vertices.keySet()) {

        if (hospitals.contains(key)) {
          continue;
        }

        countDistances += distances.get(key);
      }

      minDistance = Math.min(minDistance, countDistances);
    }

    System.out.println(minDistance);
  }

  private static void addEdge(int[] currentInput, Map<Integer, List<Vertex>> map, HashSet<Integer> hospitals) {

    addDirectedEdge(currentInput[0], currentInput[1], currentInput[2],  map, hospitals);
    addDirectedEdge(currentInput[1], currentInput[0], currentInput[2],  map, hospitals);

  }

  private static void addDirectedEdge(int from, int to, int weight, Map<Integer, List<Vertex>> map, HashSet<Integer> hospitals) {
    if (!map.containsKey(from)) {
      map.put(from, new ArrayList<>());
    }


    if (hospitals.contains(from)) {

      map.get(from).add(new Vertex(to, weight, true));

    } else {

      map.get(from).add(new Vertex(to, weight));

    }
  }

  public static HashMap<Integer, Integer> dijkstra(Integer vertex, Map<Integer, List<Vertex>> map){

    HashSet<Integer> used = new HashSet<>();
    PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
    HashMap<Integer, Integer> distances = new HashMap<>();


    queue.add(new Vertex(vertex, 0));
    distances.put(vertex, 0);

    while (!queue.isEmpty()) {

      Vertex current = queue.poll();
      if (used.contains(current.point)) {
        continue;
      }

      used.add(current.point);

      for (Vertex neighbour : map.get(current.point)) {
        if (used.contains(neighbour.point)) {
          continue;
        }

        int newDistanceToNeighbour = distances.get(current.point) + neighbour.weight;

        if (!distances.containsKey(neighbour.point)) {
          distances.put(neighbour.point, newDistanceToNeighbour);
          queue.offer(new Vertex(neighbour.point, newDistanceToNeighbour));
        } else {

          int currentDistanceToNeighbour = distances.get(neighbour.point);

          if (newDistanceToNeighbour < currentDistanceToNeighbour) {
            distances.put(neighbour.point, newDistanceToNeighbour);
            queue.offer(new Vertex(neighbour.point, newDistanceToNeighbour));
          }
        }
      }
    }

    return distances;
  }

  static class Vertex implements Comparable<Vertex>{

    private int point;
    private  int weight;
    boolean isHospital;

    Vertex(int point, int weight) {
      this.point = point;
      this.weight = weight;
      this.isHospital = false;
    }

    Vertex (int point, int weight, boolean isHospital) {
      this(point, weight);
      isHospital = true;
    }


    @Override
    public int compareTo(Vertex vertex) {
      if (weight > vertex.weight) {
        return 1;
      } else if (weight < vertex.weight){
        return -1;
      }
      return 0;
    }
  }

}