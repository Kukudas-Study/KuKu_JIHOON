import java.io.*;
import java.util.*;

public class Main {
    
    static int w, h, count;
    static int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dy = {0, 1, -1, 1, -1, 0, 1, -1};
    
    static int[][] graph;
    static boolean[][] visited;
    static ArrayList<int[]> list;
    
    static StringTokenizer st;
    static BufferedReader br;
    
    public static void main(String[] args) throws IOException, NumberFormatException {
        br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            
            if (w == 0 && h == 0) break;  // 마지막 줄 처리
            setGraph();

            // 리스트를 순회하는 동안 bfs를 반복적으로 수행한다.
            
            for (int i = 0; i < list.size(); i++) {
                int startX = list.get(i)[0];
                int startY = list.get(i)[1];
                bfs(startX, startY);
            }
            
            System.out.println(count);
        }
        
        br.close();
    }

  
    // 그래프와 방문 배열을 초기화하는 메서드
  
    static void setGraph() throws IOException, NumberFormatException {
      
        // 테스트마다 그래프, 방문 배열, 리스트 초기화
      
        graph = new int[h][w];
        visited = new boolean[h][w];
        list = new ArrayList<>();
      
        count = 0;
        
        // 땅의 위치 ({i, j})를 리스트에 저장.
      
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) list.add(new int[] {i, j});
            }
        }
    }

  
    // BFS를 통해 섬을 탐색하는 메서드
  
    static void bfs(int startX, int startY) {
      
        // 이미 방문한 위치라면 return한다.
      
        if (visited[startX][startY]) return;

        // BFS 탐색을 위한 큐를 초기화한다.
        // 큐에 시작 위치를 추가한다.
        // 시작 위치를 방문했다고 표시한다.
      
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
          
            // 큐에서 첫번째 원소를 꺼낸다.
            // 해당 원소를 기준삼아 (상하좌우, 대각선) 방향별로 이동하여 올바른 위치인지 확인한다.
            // 올바른 위치라면 이미 방문한 곳인지, 바다에 해당하는지 확인한다.
            // 그렇지 않다면 해당 위치를 방문했다고 표시하며, 큐에 해당 위치를 추가한다.

            int[] loc = queue.poll();
            
            for (int i = 0; i < 8; i++) {
                int x = loc[0] + dx[i];
                int y = loc[1] + dy[i];
                if (x >= 0 && y >= 0 && x < h && y < w) {
                    if (!visited[x][y] && graph[x][y] == 1) {
                        queue.offer(new int[] {x, y});
                        visited[x][y] = true;    
                    }
                }
            }
        }

        // bfs를 마칠 때마다 섬의 개수를 증가시킨다.
      
        count++;
    }
}
