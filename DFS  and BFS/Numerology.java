import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Numerology {

  public static int [] digitsCounter = new int[10];

  public static void main(String[] args) throws IOException {

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    List<Integer> digits = Arrays.stream(bf.readLine().split(""))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());

    count(digits);

    for (int digit : digitsCounter) {
      System.out.print(digit + " ");
    }


  }

  private static void count(List<Integer> digits) {

    if (digits.size() == 1){
      digitsCounter[digits.get(0)]++;
      return;
    }


    for (int i = 0; i < digits.size(); i++) {

      if (i == digits.size() - 1) {
        continue;
      }

      int a = digits.get(i);
      int b = digits.get(i + 1);

      int newDigit = ((a + b) * (a ^ b)) % 10;


      digits.set(i + 1,newDigit);
      digits.remove(i);

      count(digits);

      digits.set(i, b);
      digits.add(i , a);
    }

  }
}