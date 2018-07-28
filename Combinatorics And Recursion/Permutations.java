import java.util.ArrayList;
import java.util.Scanner;

public class Permutations {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    ArrayList<String> variations = new ArrayList<>();
    String str = generateString(n);
    ArrayList<String> res = permute(str, 0, n - 1, variations);
    res.stream().sorted().forEach(System.out::println);
  }

  private static String generateString(int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= n; i++) {
      sb.append(i);
    }
    return sb.toString();
  }

  private static String swap(String str, int i, int j) {
    char temp;
    char[] arr = str.toCharArray();
    temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    return String.valueOf(arr);
  }

  private static ArrayList<String> permute(String str, int i, int j, ArrayList<String> variations){
    if (i == j) {
      variations.add(str);
    } else {
      for (int k = i; k <= j; k++) {
        str = swap(str, i, k);
        permute(str, i + 1, j, variations);
        str = swap(str, i, k);
      }
    }
    return variations;
  }
}