import java.util.Scanner;

public class SpellCaster {

  static StringBuilder createSequence(String[] words) {

    StringBuilder sequence = new StringBuilder();
    int index = 1;
    while (true) {
      boolean expressionIsReady = true;
      for (int i = 0; i < words.length; i++) {
        String anInput = words[i];
        if (anInput.length() - index >= 0) {
          sequence.append(anInput.charAt(anInput.length() - index));
          expressionIsReady = false;
        }
      }
      index++;
      if(expressionIsReady)break;
    }
    return sequence;
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String[] input = in.nextLine().split(" ");
    StringBuilder sequence = createSequence(input);

    for (int i = 0; i < sequence.length(); i++) {
      StringBuilder temp = new StringBuilder();
      String strChar = String.valueOf(sequence.charAt(i)).toUpperCase();
      char currChar = sequence.charAt(i);
      int newPosition = ((strChar.charAt(0) - 64)+i) % sequence.length();
      sequence.deleteCharAt(i);
      for (int j = 0; j < newPosition; j++) {
        temp.append(sequence.charAt(j));
      }
      temp.append(currChar);
      for (int q = newPosition; q < sequence.length(); q++) {
        temp.append(sequence.charAt(q));
      }
      sequence.delete(0,sequence.length());
      sequence.append(temp);
    }
    System.out.println(sequence);
  }
}