import java.util.*;
import java.io.*;

// 중복 없이 수열을 만들어야 하므로 방문 배열을 이용해야 함.

public class Main {

    static int N, M;
    static boolean[] visited;
    static int[] seq;

    static BufferedWriter bw;
    
    public static void main(String[] args) throws Exception {
        // 입출력 준비
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        seq = new int[M];

        // dfs 시작
        dfs(0);

        // 종료
        br.close();
        bw.flush();
        bw.close();
    }

    static void dfs(int depth) throws Exception {
      
        // depth가 M에 도달하면 재귀호출을 종료하고 수열을 출력함.
      
        if(depth == M) {
            for(int i=0; i<seq.length; i++) {
                bw.write(seq[i] + " ");
            }
            bw.newLine();
            return;
        }

        // 그렇지 않으면 요소들을 순회하여 수열을 채움.
        // 현재 칸을 채우면 다음 칸을 채우기 위해 재귀호출함. 
      
        for(int i=1; i<=N; i++) {
            if(visited[i] == false) {
                visited[i] = true;   // 현재 요소 방문 표시
                seq[depth] = i;
                dfs(depth+1);        // 다음 깊이로 진행
                visited[i] = false;  // 현재 요소 방문 해제
            }
            
        }
   
    }
}
