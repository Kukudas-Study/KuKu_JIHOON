// [BOJ] 11047. 동전 0

import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException, NumberFormatException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // 동전의 종류
    int K = Integer.parseInt(st.nextToken()); // 동전의 총합

    // 동전 가격을 내림차순으로 정렬함.
    int[] coins = new int[N];

    for (int i = N - 1; i >= 0; i--) {
      coins[i] = Integer.parseInt(br.readLine());
    }

    int count = 0; // 동전의 개수 

    for (int i = 0; i < N; i++) {
      count += K / coins[i];   // count에 몫을 추가한다.
      K = K % coins[i];        // 잔액은 나머지로 업데이트한다.
    }

    System.out.println(count);
    br.close();

  }
}
