// [BOJ] 2775. 부녀회장이 될테야

import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException, NumberFormatException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine()); // 테스트 횟수

    for (int t = 0; t < T; t++) {

      int k = Integer.parseInt(br.readLine()); // k층
      int n = Integer.parseInt(br.readLine()); // n호

      // 아파트 인원 수가 저장될 2차원 배열을 생성한다.
      int[][] house = new int[k + 1][n + 1];

      // 0층의 i호에는 i명이 산다.
      for (int i = 1; i <= n; i++) {
        house[0][i] = i;
      }

      // i층 j호 인원은 i-1층 j호 인원과 i층 j-1호 인원의 합이다.
      // 동적 계획법을 통해 2차원 배열을 채운다.
      for (int i = 1; i <= k; i++) {
        for (int j = 1; j <= n; j++) {
          house[i][j] = house[i - 1][j] + house[i][j - 1];
        }
      }

      bw.write(String.valueOf(house[k][n]));
      bw.newLine();

    }

    bw.flush();
    bw.close();
    br.close();

  }
}
