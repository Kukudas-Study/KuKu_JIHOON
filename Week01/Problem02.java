import java.io.*;
import java.util.*;

// [BOJ] 2164. 카드 2

public class Main {
  public static void main(String[] args) {
    // deque : [N, N-1, N-2, ..., 2, 1]
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 1; i <= N; i++) {
      deque.addFirst(i);
    }

    while(deque.size() > 1) {
      deque.removeLast();
      deque.addFirst(deque.removeLast());
    }

    System.out.println(deque.getFirst());
  }
}
