import java.io.*;
import java.util.*;


public class MessagesInBottle {

  private static int combinations = 0;
  private static TreeSet<String> set = new TreeSet();
  private static ArrayList<Long> usedDigits = new ArrayList();

  public static void main(String[] args) throws IOException {
    OutputWriter out = new OutputWriter();
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String secretMessage = bf.readLine();
    int[] digits = Arrays.stream(secretMessage.split("")).mapToInt(Integer::parseInt).toArray();
    char[] cipher = bf.readLine().toCharArray();
    HashMap<Character, String> map = new HashMap<>();
    fillMap(cipher, map);
    findCombinations(secretMessage, 0, digits, map, new StringBuilder());
    ArrayList<String> list = new ArrayList<>(set);
    System.out.println(combinations);
    list.forEach(out::printLine);
    out.close();

  }

  private static void findCombinations(String secretMessage, int index, int digits[],
                                       HashMap<Character, String> map, StringBuilder result) {

    if (index == digits.length){
      ++combinations;
      set.add(result.toString());
      return;
    }


    for (Character key : map.keySet()) {
      if (secretMessage.substring(index).startsWith(map.get(key))){
        result.append(key);
        findCombinations(secretMessage, index + map.get(key).length(), digits, map, result);
        result.deleteCharAt(result.length() - 1);
      }
    }
  }


  private static void fillMap(char[] cipher, HashMap<Character, String> map) {

    for (int i = 0; i < cipher.length; i++) {
      if (Character.isAlphabetic(cipher[i])) {
        char key = cipher[i];
        StringBuilder value = new StringBuilder();

        while (i + 1 < cipher.length && Character.isDigit(cipher[i + 1])) {
          value.append(cipher[i + 1]);
          ++i;
        }
        map.put(key, value.toString());
      }
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