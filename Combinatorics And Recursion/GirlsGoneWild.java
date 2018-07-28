import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class GirlsGoneWild {

  private static boolean[] used;
  private static SortedSet<String> result = new TreeSet<>();
  private static ArrayList<Character>  usedLetters = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int shirts = Integer.parseInt(bf.readLine());
    char[] skirts = bf.readLine().toCharArray();
    int girls = Integer.parseInt(bf.readLine());
    used = new boolean[skirts.length];

    for (int i = 0; i < shirts; i++) {
      for (int j = 0; j < skirts.length; j++) {
        StringBuilder start = new StringBuilder().append(i).append(skirts[j]);

        if(usedLetters.contains(skirts[j])) {
          continue;
        }
        usedLetters.add(skirts[j]);
        used[j] = true;
        girlsGoneWild(start, j + 1, skirts, shirts, i + 1, girls, 1);
        used[j] = false;
      }
      usedLetters.clear();
    }

    System.out.println(result.size());
    for (String str:result) {
      System.out.println(str);
    }

  }


  private static void girlsGoneWild(StringBuilder current, int skirtsIndex,
                                    char[] skirts, int shirts, int shirtsIndex,
                                    int girls, int girlIndex) {

    if(girlIndex == girls){
      result.add(current.toString());
      return;
    }

    for (int i = shirtsIndex; i < shirts; i++) {
      for (int j = 0; j < skirts.length; j++) {

        if (used[j]) {
          continue;
        }

        used[j] = true;

        girlsGoneWild(current.append("-").append(i).append(skirts[j]),
                j + 1, skirts, shirts, i + 1, girls,  girlIndex + 1);
        current.delete(current.length() - 3, current.length());

        used[j] = false;

      }
    }
  }
}