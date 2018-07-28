import java.util.Scanner;

public class Kitty {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String inputField = in.nextLine();
    String[] inputSteps = in.nextLine().split(" ");
    int[] steps = new int[inputSteps.length];
    for (int i = 0; i < steps.length; i++) {
      steps[i] = Integer.parseInt(inputSteps[i]);
    }
    int food = 0; int deadlocks = 0; int souls = 0; int jumps = 0; int index = 0; boolean isDeadlocked = false;
    char[] chars = inputField.toCharArray();
    boolean[] isChecked = new boolean[chars.length];


    for (int i = 0; i < steps.length+1; i++) {
      if (chars[index] == '*' && !isChecked[index]) {
        food++;
        isChecked[index] = true;
      }else if (chars[index] == '@' && !isChecked[index]) {
        souls++;
        isChecked[index] = true;
      } else if (chars[index] == 'x'){
        if (index % 2 == 0 ) {
          souls--; deadlocks++;
          chars[index] = '@';
        }else {
          food--; deadlocks++;
          chars[index] = '*';
        }
      }
      if (souls < 0 || food < 0) {
        isDeadlocked = true;
        break;
      }
      jumps++;
      if(i == steps.length) {break;}
      index = (index + steps[i]) % chars.length;
      if (index < 0) { index = (chars.length+index) % chars.length;}
    }

    if (isDeadlocked) {
      System.out.println("You are deadlocked, you greedy kitty!\nJumps before deadlock: " + jumps );
    }else {
      System.out.println("Coder souls collected: " + souls +
              "\nFood collected: " + food +
              "\nDeadlocks: " + deadlocks);
    }
  }
}