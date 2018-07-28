import java.util.ArrayList;
import java.util.Scanner;

public class Indices {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    in.nextLine();
    String[] input = in.nextLine().split(" ");
    int [] numbers = new int[input.length];
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = Integer.parseInt(input[i]);
    }

    ArrayList<Integer> list = new ArrayList<>();
    list.add(0);
    boolean[] isVIsited = new boolean[numbers.length];
    int index = 0; isVIsited[0] = true;
    boolean isPrinted = false;

    while (true) {

      if (numbers[index] < 0 || numbers[index] >= numbers.length) {
        break;
      }

      if (isVIsited[numbers[index]]) {
        printCycle(list, numbers[index]);
        isPrinted = true;
        break;
      }

      list.add(numbers[index]);
      isVIsited[numbers[index]] = true;
      index = numbers[index];

    }
    if (!isPrinted) {
      for (int el:
              list) {
        System.out.print(el + " ");
      }
    }
  }

  private static void printCycle(ArrayList<Integer> list, int startCycle ) {
    StringBuilder output = new StringBuilder();
    for (int el:
            list) {
      if (el == startCycle) {
        output.append("(").append(startCycle);
      } else {
        output.append(" ").append(el);
      }
    }

    StringBuilder out = new StringBuilder().append(output.substring(0,output.length() - 1));
    System.out.println(output.append(")"));
  }
}