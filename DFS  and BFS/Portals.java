import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Portals {

  private static int maxPower = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] rowColInput = bf.readLine().split(" ");
    int startRow = Integer.parseInt(rowColInput[0]);
    int startCol = Integer.parseInt(rowColInput[1]);

    String[] matrixSize = bf.readLine().split(" ");
    int n = Integer.parseInt(matrixSize[0]);
    int m = Integer.parseInt(matrixSize[1]);

    int[][] matrix = new int[n][m];
    fillMatrix(bf, n, m, matrix);

    findMaxTeleportingPower(0, matrix, startRow, startCol, 0);
    System.out.println(maxPower);

  }

  private static void findMaxTeleportingPower(int currentPower, int[][] matrix, int row, int col, int portalPower) {


    if (row >= matrix.length || row < 0
            || col >= matrix[0].length || col < 0) {
      return;
    }

    if (matrix[row][col] == -1) {
      return;
    }

    currentPower += portalPower;
    maxPower = Math.max(currentPower, maxPower);

    if (matrix[row][col] == 0) {
      return;
    }


    portalPower = matrix[row][col];
    matrix[row][col] = 0;

    findMaxTeleportingPower(currentPower, matrix, row + portalPower, col, portalPower);
    findMaxTeleportingPower(currentPower, matrix, row, col + portalPower, portalPower);
    findMaxTeleportingPower(currentPower , matrix, row - portalPower, col, portalPower);
    findMaxTeleportingPower(currentPower, matrix, row, col - portalPower, portalPower );

    matrix[row][col] = portalPower;

  }

  private static void fillMatrix(BufferedReader bf, int n, int m, int[][] matrix) throws IOException {
    for (int i = 0; i < n; i++) {
      char[] currentINput = bf.readLine().replaceAll(" ", "").toCharArray();
      for (int j = 0; j < m; j++) {

        if (currentINput[j] == '#') {
          matrix[i][j] = -1;
        } else {
          matrix[i][j] = currentINput[j] - '0';
        }
      }
    }
  }
}