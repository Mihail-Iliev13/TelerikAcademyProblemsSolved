import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class PlayerRanking {

  private static HashMap<String, TreeSet<Player>> playersByType;
  private static ArrayList<Player> playersByPosition;
  private static StringBuilder output;

  public static void main(String[] args) throws IOException {
    playersByType = new HashMap<>();
    playersByPosition = new ArrayList<>();
    output = new StringBuilder();

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] input = bf.readLine().split(" ");

    while (!input[0].equals("end")) {

      switch (input[0]) {
        case "add":
          String name = input[1];
          String type = input[2];
          int age = Integer.parseInt(input[3]);
          int position = Integer.parseInt(input[4]);
          addPlayer(name, type, age, position);
          break;
        case "find":
          findPlayersByType(input[1]);
          break;
        case "ranklist":
          int from = Integer.parseInt(input[1]);
          int to = Integer.parseInt(input[2]);
          showRanking(from, to);
          break;
        default:
          break;
      }

      input = bf.readLine().split(" ");
    }

    System.out.println(output);

  }

  private static void showRanking(int from, int to) {

    boolean shoudDelete = false;
    for (int i = from - 1; i < to; i++) {
      shoudDelete = true;
      output.append(i + 1);
      output.append(". ");
      output.append(playersByPosition.get(i));
      output.append("; ");
    }

    if (shoudDelete) {
      output.deleteCharAt(output.length() - 1);
      output.deleteCharAt(output.length() - 1);
    }

    output.append("\n");

  }

  private static void findPlayersByType(String type) {

    output.append("Type ");
    output.append(type);
    output.append(": ");

    if (!playersByType.containsKey(type)) {
      output.append("\n");
      return;
    }

    playersByType.get(type).stream()
            .limit(5)
            .forEach(player -> output.append(player).append("; "));

    if (output.charAt(output.length() - 2) == ';') {

      output.deleteCharAt(output.length() -1);
      output.deleteCharAt(output.length() -1);

    }

    output.append("\n");

  }

  private static void addPlayer(String name, String type, int age, int position) {
    Player newPlayer = new Player(name, type, age, position);

    if (!playersByType.containsKey(type)) {
      playersByType.put(type, new TreeSet<>());
    }

    playersByType.get(type).add(newPlayer);

    if (position > playersByPosition.size()) {
      playersByPosition.add(newPlayer);
    } else {
      playersByPosition.add(position - 1, newPlayer);
    }

    output.append("Added player ");
    output.append(name);
    output.append(" to position ");
    output.append(position);
    output.append("\n");

  }

  private static class Player implements  Comparable<Player>{
    private String name;
    private String type;
    private  int age;
    private int position;

    public Player(String name, String type, int age, int position) {
      this.name = name;
      this.type = type;
      this.age = age;
      this.position = position;
    }


    @Override
    public int compareTo(Player player) {

      if (name.compareTo(player.name) == 0) {

        if (age > player.age) {
          return -1;
        } else {
          return 1;
        }
      } else {
        return name.compareTo(player.name);
      }

    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(name);
      sb.append("(");
      sb.append(age);
      sb.append(")");
      return sb.toString();
    }
  }
}
