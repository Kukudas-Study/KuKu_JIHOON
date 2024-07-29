import java.io.*;
import java.util.Scanner;

public class Main {
    
    static int N, M;
    static int[] seq;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        seq = new int[M];
        
        dfs(0);
        
        sc.close();
        bw.flush();
        bw.close();
    }
    
    static void dfs(int depth) throws IOException {
      
        // depth가 M에 도달하면 재귀 호출을 종료하고
        // seq 배열에 저장된 M개의 숫자를 출력.
      
        if(depth == M) {
            for(int num : seq) {
                bw.write(String.valueOf(num) + " ");
            }
            bw.newLine();
            return;
        }

        // 그렇지 않으면 계속 탐색함
        // 모든 경우의 수를 한번씩만 출력해야 하므로 조건을 설정하지 않음.
        
        for(int i=1; i<=N; i++) {
           seq[depth] = i;
           dfs(depth + 1);
        }
        
    }

}
