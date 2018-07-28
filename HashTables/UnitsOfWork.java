import java.io.*;
import java.util.*;


public class UnitsOfWork {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    OutputWriter out = new OutputWriter();
    String[] commands = bf.readLine().split(" ");
    HashMap<String, Unit> allHeroes = new HashMap<>();
    HashMap<String, TreeSet<Unit>> byType = new HashMap<>();
    TreeSet<Unit> allHeroesOrdered = new TreeSet<>();
    StringBuilder result = new StringBuilder();

    while (!commands[0].equals("end")) {

      switch (commands[0]) {
        case "add":
          add(commands, allHeroes, allHeroesOrdered, byType, result);
          break;
        case "remove":
          remove(commands, allHeroes, allHeroesOrdered, byType, result);
          break;
        case "find":
          find(commands, byType, result);
          break;
        case "power":
          power(commands, allHeroesOrdered, result);
          break;
        default:
          break;
      }

      commands = bf.readLine().split(" ");
    }
    out.printLine(result);
    out.close();
  }

  private static void power(String[] commands, TreeSet<Unit> orderedHeroes, StringBuilder result) {

    int power = Integer.parseInt(commands[1]);
    result.append("RESULT: ");
    Iterator<Unit> itr = orderedHeroes.iterator();
    int count = 0;

    while (itr.hasNext()) {

      Unit current = itr.next();
      result.append(current.name).append("[").append(current.type)
              .append("]").append("(").append(current.attack).append("), ");
      count++;

      if (count == power) {
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        break;
      }

    }


    result.append("\n");
  }

  private static void find(String[] commands, HashMap<String, TreeSet<Unit>> byType, StringBuilder result) {

    if (!byType.containsKey(commands[1])) {
      result.append("RESULT: \n");
      return;
    }

    result.append("RESULT: ");
    boolean isAppended = false;
    Iterator<Unit> itr = byType.get(commands[1]).iterator();

    int count = 0;
    while (itr.hasNext()) {

      isAppended = true;
      Unit current = itr.next();
      result.append(current.name).append("[").append(current.type)
              .append("]").append("(").append(current.attack).append("), ");

      count++;

      if (count == 10) {
        break;
      }

    }


    if (isAppended) {
      result.deleteCharAt(result.length() - 1);
      result.deleteCharAt(result.length() - 1);
    }

    result.append("\n");
  }

  private static void remove(String[] commands, HashMap<String, Unit> allHeroes, TreeSet<Unit> orederedUnits,
                             HashMap<String, TreeSet<Unit>> byType, StringBuilder result) {

    if (!allHeroes.containsKey(commands[1])) {
      result.append("FAIL: ").append(commands[1]).append(" could not be found!\n");
      return;
    }

    Unit current = allHeroes.get(commands[1]);
    byType.get(current.type).remove(current);
    allHeroes.remove(current.name);
    orederedUnits.remove(current);
    result.append("SUCCESS: ").append(current.name).append(" removed!\n");
  }

  private static void add(String[] commands, HashMap<String, Unit> allHeroes, TreeSet<Unit> orderedUnits,
                          HashMap<String, TreeSet<Unit>> byType, StringBuilder result) {

    if (allHeroes.containsKey(commands[1])) {
      result.append("FAIL: ").append(commands[1]).append(" already exists!\n");
      return;
    }

    Unit current = new Unit(commands[1], commands[2], Integer.parseInt(commands[3]));
    allHeroes.put(current.name, current);
    String key = getOrCreate(current, byType);
    byType.get(key).add(current);
    orderedUnits.add(current);
    result.append("SUCCESS: ").append(current.name).append(" added!\n");
  }

  private static String getOrCreate(Unit current, HashMap<String, TreeSet<Unit>> byType) {

    if (!byType.containsKey(current.type)) {
      byType.put(current.type, new TreeSet<>());
    }

    return current.type;
  }

  public static class Unit implements Comparable<Unit>{
    String name;
    String type;
    int attack;

    Unit(String name, String type, int attack) {
      this.name = name;
      this.type = type;
      this.attack = attack;

    }

    public int getAttack() {
      return attack;
    }

    public String getName() {
      return name;
    }

    @Override
    public int compareTo(Unit unit) {
      int compare = Integer.compare(this.attack, unit.attack);

      if (compare == 0) {
        return compare = name.compareTo(unit.name);
      }

      return -compare;
    }

  }

  static class OutputWriter {
    private final PrintWriter writer;

    OutputWriter() {
      writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }

    void print(Object... objects) {
      for (int i = 0; i < objects.length; i++) {
        if (i != 0)
          writer.print(' ');
        writer.print(objects[i]);
      }
    }

    void printLine(Object... objects) {
      print(objects);
      writer.println();
    }

    void close() {
      writer.close();
    }
  }

}