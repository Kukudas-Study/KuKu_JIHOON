// [BOJ] 1010. 다리놓기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int result = combination(M, N);  // M개에서 N개를 선택하는 조합의 수
            System.out.println(result);
        }
        
    }

    static int combination(int M, int N) {
        int[][] comb = new int[M+1][M+1];

        for(int i=0; i<=M; i++) {
            comb[i][0] = 1;   
        }

        for(int i=1; i<=M; i++) {
            for(int j=1; j<=M; j++) {
                comb[i][j] = comb[i-1][j] + comb[i-1][j-1];
            }
        }

        return comb[M][N];
    }

    
}
