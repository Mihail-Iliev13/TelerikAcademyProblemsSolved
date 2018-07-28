import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;


public class BracketsExpression {

  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(System.in);
    char[] input = in.nextLine().toCharArray();
    ArrayDeque<String> stack = new ArrayDeque<>();

    for (int i = input.length - 1; i >= 0; i--) {

      if (input[i] == ')') {
        createExpression(input, i, 0, 0, stack);
      }
    }

    while (stack.size() > 0){
      System.out.println(stack.peek());
      stack.pop();
    }
  }

  private static void createExpression(char[] input, int i,
                                       int opening, int closing, ArrayDeque<String> stack) {

    StringBuilder sb = new StringBuilder();
    do{
      sb.append(input[i]);

      if (input[i] == ')') {
        closing++;
      }

      if (input[i] == '(') {
        opening++;
      }
      --i;
    } while (opening != closing);

    stack.push(sb.reverse().toString());
  }
}