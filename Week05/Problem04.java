import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static BufferedWriter bw;
	
	static int[] input, output;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        output = new int[M];
        
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) {
        	input[i] = Integer.parseInt(st.nextToken());
        }

        // 입력치를 순서대로 정렬함.
        Arrays.sort(input);
        
        dfs(0);
        
        bw.flush();
        bw.close();
        br.close();
	}
	
	static void dfs(int depth) throws Exception {
		if(depth == M) {
			for(int num : output) {
        bw.write(Integer.valueOf(num) + " ");
      }
      bw.newLine();
			return;
		}

    // 이번 문제에는 중복을 허용하므로 방문 배열을 사용하지 않음.
    // 하지만 현재 요소가 이전 요소보다 작을 때는 재귀호출하지 않음. 
		for(int i=0; i<N; i++) {
			output[depth] = input[i];
			if(depth > 0) {
				if(output[depth] < output[depth-1]) continue; 
			}
			dfs(depth + 1);
		}
	}
}
