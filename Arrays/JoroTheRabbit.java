import java.util.Scanner;

public class JoroTheRabbit {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int[] field = fillArray(in.nextLine().split(", "));
    int maxVisited = 1;
    int currentVisited = 1;

    for (int startPosition = 0; startPosition < field.length; startPosition++) {
      int step = 1;

      while (step < field.length) {

        int currentPosition = startPosition;
        int current = field[currentPosition];
        int nextPosition = (startPosition + step) % field.length;
        int next = field[nextPosition];

        while (current < next &&
                currentPosition != nextPosition ){
          ++currentVisited;
          current = next;
          currentPosition = nextPosition;
          nextPosition = (currentPosition + step) % field.length;
          next = field[nextPosition];

        }

        maxVisited = Math.max(currentVisited, maxVisited);
        currentVisited = 1;
        step++;
      }

    }
    System.out.println(maxVisited);
  }

  private static int[] fillArray(String[] strArr) {
    int[] arr = new int[strArr.length];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = Integer.parseInt(strArr[i]);
    }
    return arr;
  }
}