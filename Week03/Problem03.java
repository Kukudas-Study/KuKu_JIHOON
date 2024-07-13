import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, NumberFormatException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 카드의 수
        int M = Integer.parseInt(st.nextToken()); // 목표치
        int answer = 0;                           // 카드 3장의 최대합

      
        // N장의 카드들을 배열에 저장합니다.
      
        st = new StringTokenizer(br.readLine());
        int[] cards = new int[N];

        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }


        // N장의 카드들 중 3장을 선택한 다음, 합을 계산합니다.
        // 3장의 합이 목표치 M 이하이면 합을 최대합과 비교합니다.
        // 3장의 합이 최대합보다 크면 최대합을 갱신합니다.

        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    int sum = cards[i] + cards[j] + cards[k];
                    if (sum <= M) {
                        answer = Math.max(answer, sum);
                    }
                }
            }
        }

        System.out.println(answer);
        br.close();
    }
}
