import java.util.*;
import java.io.*;

public class Main {
    
    static int M, N, K, count;
    static BufferedReader br;
    static StringTokenizer st;
    
    static boolean[][] visited;
    static int[][] graph;
    static ArrayList<int[]> list;
  
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());  // 테스트 횟수
        
        for(int t=0; t<T; t++) {
            // 테스트마다 전역변수와 자료구조를 초기화한다.
          
            initialize();

            // 리스트를 순회하여 bfs를 반복한다.

            for(int i=0; i<list.size(); i++) {
                int startX = list.get(i)[0];
                int startY = list.get(i)[1];
                bfs(startX, startY);
            }
          
            System.out.println(count);
        }
        
        br.close();
    }

  
    // 1단계 : 초기화
  
    public static void initialize() throws Exception {
 
        /* 입력 준비 */
        
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  // 가로 길이
        N = Integer.parseInt(st.nextToken());  // 세로 길이
        K = Integer.parseInt(st.nextToken());  // 배추 갯수
        count = 0;
          
        /* 배열과 리스트 초기화 */
        
        visited = new boolean[M][N];
        list = new ArrayList<>();
        graph = new int[M][N];
      
        for(int k=0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            
            graph[i][j] = 1;
            list.add(new int[] {i, j});
        }  
    }
  
    
    // 2단계 : bfs
  
    public static void bfs(int startX, int startY) {

        // 이미 방문한 위치라면 return한다.
        if(visited[startX][startY] == true) return;

        // BFS 탐색을 위한 큐를 초기화한다.
        // 큐에 시작 위치를 추가한다.
        // 시작 위치를 방문했다고 표시한다.
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startX, startY});
        visited[startX][startY] = true;
  
        while(!queue.isEmpty()) {
            // 큐에서 첫번째 원소를 꺼낸다.
            // 해당 원소를 기준삼아 방향별로 이동하여 올바른 위치인지 확인한다.
            // 올바른 위치라면 이미 방문한 곳인지, 배추가 없는 곳인지 확인한다.
            // 그렇지 않다면 해당 위치를 방문했다고 표시하며, 큐에 해당 위치를 추가한다.
            int[] loc = queue.poll();
            for(int i=0; i<4; i++) {
                int x = loc[0] + dx[i]; 
                int y = loc[1] + dy[i];  
                
                if((x >= 0) && (y >= 0) && (x < M) && (y < N)) {
                    if(visited[x][y] == false && graph[x][y] != 0) {
                        visited[x][y] = true;
                        queue.offer(new int[] {x, y});
                    }
                }
            }
        }

        // 이번 bfs 탐색이 끝나면 지렁이의 개수를 하나 증가시킨다. 
        count++;
    }
}
