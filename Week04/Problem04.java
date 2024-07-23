import java.util.*;

class Solution {
    
    static int n, m;
    
    static boolean[][] visited;
    static int[][] graph;
    static List<Integer>[] oilList;

    // 4방향으로 이동하기 위한 배열
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] land) {

        // 1단계 : 초기화
      
        n = land.length;     // 그래프의 세로 길이
        m = land[0].length;  // 그래프의 가로 길이
        
        graph = land;
        visited = new boolean[n][m];   // 방문 배열
        oilList = new ArrayList[m];    // 시추 배열
      
        for(int i = 0; i < m; i++) {
            oilList[i] = new ArrayList<Integer>();
        }
      

        // 2단계 : bfs
      
        // 그래프를 순회하며 Bfs를 반복함.
        // 석유가 없거나, 이전 bfs 때 탐색했던 곳이라면 bfs를 할 필요 없음.
      
        for (int i = 0; i < n; i++) { 
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == false && graph[i][j] == 1) {
                    bfs(new int[] {i, j});
                }
            }
        }

        // 3단계 : 최대 석유량 계산
      
        int maxOil = 0;
        
        for(int i=0; i<m; i++) {
            int currentOil = 0;
            for(Integer count : oilList[i]) {
                currentOil += count;
            } 
            maxOil = Math.max(maxOil, currentOil);
        }

        return maxOil;
    }
  
    
    static void bfs(int[] start) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        
        Set<Integer> ySet = new HashSet<>();
        ySet.add(start[1]);
        int count = 0;

        while(!queue.isEmpty()) {
            // 큐에서 한 개씩 뽑을 때마다 덩어리 크기를 증가시킨다.
            int[] current = queue.poll();
            count++;

            // 네 방향으로 이동
            for(int i=0; i<4; i++) {
                int x = current[0] + dx[i];
                int y = current[1] + dy[i];

                // 올바른 위치인지 확인하고, 아직 방문하지 않은 석유 칸인지 확인한다.
                // 조건을 만족하면 방문배열과 큐를 업데이트하고, Set에 y값을 추가한다.
                if(x >= 0 && y >= 0 && x < n && y < m) {
                    if(visited[x][y] == false && graph[x][y] != 0) {
                        queue.add(new int[] {x, y});
                        visited[x][y] = true;
                        ySet.add(y);
                    }
                }
                
            }
            
        }
        
        // 이번 bfs에 연관된 열에 석유 덩어리의 크기를 저장한다.
        for(Integer y : ySet) {
            oilList[y].add(count);
        }
        

    }
}
