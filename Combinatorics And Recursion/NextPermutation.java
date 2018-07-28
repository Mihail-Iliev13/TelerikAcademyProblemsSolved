import java.util.Scanner;

public class NextPermutation {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    in.nextLine();
    String[] currentPerm = in.nextLine().split(" ");
    System.out.println(findNextPerm(stringArrToIntArr(currentPerm)));
  }


  private static String findNextPerm(int[] array){
    int suffixHeadIndex = findLongestNonIncreasingSuffix(array);

    if (suffixHeadIndex <= 0) {
      return "This is the last permutation";
    }

    int pivotIndex = suffixHeadIndex - 1;
    int rigtMostIndex = array.length - 1;
    while (array[rigtMostIndex] <= array[pivotIndex]) {
      rigtMostIndex--;
    }

    swap(array, rigtMostIndex, pivotIndex);
    reverse(array,suffixHeadIndex);

    return arrayToString(array);
  }

  private static void reverse(int[] array, int suffixHeadIndex) {
    int lastIndex = array.length -1;
    while (suffixHeadIndex < lastIndex) {
      int temp = array[suffixHeadIndex];
      array[suffixHeadIndex] = array[lastIndex];
      array[lastIndex] = temp;
      suffixHeadIndex++;
      lastIndex--;
    }
  }

  private static void swap(int[] array, int rigtMostIndex, int pivotIndex) {
    int temp = array[pivotIndex];
    array[pivotIndex] = array[rigtMostIndex];
    array[rigtMostIndex] = temp;
  }

  private static int findLongestNonIncreasingSuffix(int[] array) {
    int i = array.length - 1;
    while ( i  > 0 && array[i - 1]  >= array[i]) {
      i--;
    }
    return i;
  }

  private static String arrayToString(int[] array){
    StringBuilder res = new StringBuilder();
    for (int el : array) {
      res.append(el).append(" ");
    }
    return res.toString();
  }



  private static int[] stringArrToIntArr (String[] strArr) {
    int[] intArr = new int[strArr.length];
    for (int i = 0; i < intArr.length; i++) {
      intArr[i] = Integer.parseInt(strArr[i]);
    }
    return intArr;
  }

}