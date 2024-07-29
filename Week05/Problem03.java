import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int count = 0;
    static BufferedWriter bw;

    static boolean[] visited;
    static int[] input;
    static int[] output;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        visited = new boolean[N];
        input = new int[N];
        output = new int[M];
                
        st = new StringTokenizer(br.readLine());
 
        
        for(int i=0; i<N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(input);
        
        dfs(0);
        
        bw.flush();
        bw.close();
        br.close();
    }
    
    static void dfs(int depth) throws IOException {
        if(depth == M) {
            for(int num : output) {
                bw.write(Integer.valueOf(num) + " ");
            }
            bw.newLine();
            return;
        }

        // depth가 M에 도달하지 않으면 순회를 반복함.
        // 방문하지 않은 요소에 한해 해당 요소를 true로 업데이트하고,
        // dfs(다음 깊이)를 재귀호출함.
        // 호출이 끝나면 해당 요소를 다시 false로 업데이트함.
        
        for(int i=0; i<N; i++) {
           if(visited[i] == false) {  
               visited[i] = true;
               output[depth] = input[i];
               dfs(depth + 1);
               visited[i] = false;
           }  
        }
    }

}
