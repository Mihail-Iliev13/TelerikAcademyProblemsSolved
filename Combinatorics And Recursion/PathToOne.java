import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class PathToOne {

  private static TreeSet<Integer> paths = new TreeSet<>();

  public static void main(String[] args) throws IOException {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    findPathsToOne(Long.parseLong(in.readLine()), 0);
    int shortestPath = paths.first();
    System.out.println(shortestPath);

  }

  private static void findPathsToOne(long number, int counter) {

    if (number == 1) {
      paths.add(counter);
      return;
    }

    if (number % 2 == 1) {

      findPathsToOne(number + 1, counter + 1);
      findPathsToOne(number - 1, counter + 1);

    } else {

      findPathsToOne(number/2, counter + 1);

    }

  }
}