import java.util.*;
import java.io.*;

public class Main {
    
  static int N, M;
  static BufferedWriter bw;
  
  static int[] input, output;
  static boolean[] visited;
  static Set<String> seqSet;
	
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
    N = Integer.parseInt(st.nextToken());  // 배열의 길이
    M = Integer.parseInt(st.nextToken());  // 수열의 길이

    input = new int[N];
    output = new int[M];
    visited = new boolean[N];
    seqSet = new HashSet<>();
    
    st = new StringTokenizer(br.readLine());
        
    for(int i=0; i<N; i++) { 
      input[i] = Integer.parseInt(st.nextToken()); // 입력 배열 초기화
    }
        
    Arrays.sort(input);  // 입력 배열 정렬
    
    dfs(0);  // 깊이 우선 탐색 진행
    
    bw.flush();
    bw.close();
    br.close();
  }

  
  static void dfs(int depth) throws Exception {
    if(depth == M) { 
      // 깊이 m에 도달하면 재귀 호출 종료
      String seq = "";
      for(int num : output) {
        // 수열을 문자열로 만들기
        seq += Integer.valueOf(num) + " ";
      }
      
      if(!seqSet.contains(seq)) {  
        // 문자열 집합에서 해당 문자열이 존재하지 않으면 해당 문자열을 출력
        seqSet.add(seq);
        bw.write(seq);
        bw.newLine();
      }
      return;
    }
		
    for(int i=0; i<N; i++) {
      if(visited[i] == false) {
        visited[i] = true;         // 현재 요소 방문 처리
        output[depth] = input[i];  // 수열에 현재 요소 추가
        dfs(depth + 1);            // 다음 깊이로 재귀 호출
        visited[i] = false;        // 현재 요소 방문 해제
      }
			
    }
  }
}
