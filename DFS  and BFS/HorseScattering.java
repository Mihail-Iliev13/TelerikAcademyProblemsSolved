import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class HorseScattering {

  private static int[] horsePathRows = {2, 2, 1, 1, -1, -1, -2, -2};
  private static int[] horsePathCols = {-1, 1, 2, -2, -2, 2, -1, 1};
  private static boolean[][] isVisited;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(bf.readLine());
    int m = Integer.parseInt(bf.readLine());
    int r = Integer.parseInt(bf.readLine());
    int c = Integer.parseInt(bf.readLine());

    int[][] matrix = new int[n][m];
    matrix[r][c] = 1;
    isVisited = new boolean[n][m];
    isVisited[r][c] = true;
    bfs(matrix, r, c);
    int colToBePrinted = m/2;
    for (int[] row : matrix) {
      System.out.println(row[colToBePrinted]);
    }

  }

  private static void bfs(int[][] matrix, int row, int col) {
    Queue<Integer[]> queue = new ArrayDeque<>();
    Integer[] position = {row, col};
    queue.offer(position);

    while (!queue.isEmpty()) {
      Integer[] currentPosition = queue.poll();
      row = currentPosition[0];
      col = currentPosition[1];

      for (int i = 0; i < horsePathRows.length; i++) {
        int currRow = row + horsePathRows[i];
        int currCol = col + horsePathCols[i];

        if (currRow < 0 || currRow >= matrix.length || currCol < 0 || currCol >= matrix[0].length ||
                isVisited[currRow][currCol]) {
          continue;
        }


        Integer[] newPosition = {currRow, currCol};
        queue.offer(newPosition);
        matrix[currRow][currCol] = matrix[row][col] + 1;
        isVisited[currRow][currCol] = true;
      }

    }
  }
}