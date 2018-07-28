import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Swappings {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(bf.readLine());
    ListNode[] numbers = new ListNode[n];
    ListNodeChain chain = new ListNodeChain();
    for (int i = 0; i < n; i++) {
      numbers[i] = new ListNode(i + 1);
      chain.add(numbers[i]);
    }

    int[] swappers = Arrays.stream(bf.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

    for (int swapper : swappers) {
      swap(chain, numbers[swapper - 1]);
    }

    ListNode current = chain.head;
    StringBuilder sb = new StringBuilder();
    while (current != null) {
      sb.append(current.value);
      sb.append(" ");
      current = current.next;
    }

    System.out.println(sb);

  }

  private static void swap(ListNodeChain chain, ListNode node) {

    if (node.value == chain.head.value) {
      ListNode newTail = chain.head;
      ListNode newHead = chain.head.next;
      newHead.prev = null;
      chain.head.next = null;
      newTail.prev = chain.tail;
      chain.tail.next = newTail;
      chain.head = newHead;
      chain.tail = newTail;
      return;

    }

    if (node.value == chain.tail.value) {
      ListNode newTail = chain.tail.prev;
      ListNode newHead = chain.tail;
      newTail.next = null;
      newHead.prev = null;
      newHead.next = chain.head;
      chain.head.prev = newHead;
      chain.head = newHead;
      chain.tail = newTail;
      return;
    }

    ListNode newHead = node.next;
    ListNode newTail = node.prev;
    newHead.prev = null;
    newTail.next = null;
    node.next = chain.head;
    chain.head.prev = node;
    node.prev = chain.tail;
    chain.tail.next = node;
    chain.head = newHead;
    chain.tail = newTail;

  }


  private static class ListNodeChain {
    ListNode head;
    ListNode tail;

    public void add(ListNode newNode) {

      if (head == null) {
        head = newNode;
        tail = newNode;
        return;
      }

      if (head.next == null) {
        head.next = newNode;
        tail = newNode;
        tail.prev = head;
        return;
      }

      newNode.prev = tail;
      tail.next = newNode;
      tail = newNode;

    }


  }
  private static class ListNode {
    ListNode next;
    ListNode prev;
    int value;

    public ListNode(int i) {
      this.value = i;
    }
  }
}