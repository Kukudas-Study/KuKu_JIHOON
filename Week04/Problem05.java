import java.util.*;

class Solution {

    static int row, col;

    static int[][] graph;
    static boolean[][] visited;
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] maps) {

        // 정적 변수와 자료구조 초기화
      
        row = maps.length;
        col = maps[0].length;
        
        visited = new boolean[row][col];
        graph = maps;

        // bfs를 통해 탐색 시
      
        bfs();

        // 목적지에 도달하지 않으면 그래프에서 해당 위치가 업데이트되지 않으므로 -1 반환
      
        if(graph[row-1][col-1] == 1) {
            return -1;
        }

        // 목적지에 도달하면 목적지까지의 최단 경로 반환
      
        return graph[row-1][col-1];
    }
    
    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            // 현재 위치에서 상하좌우 방향별로 이동할 수 있는지 확인
            // 다음 위치가 아직 방문하지 않은 곳인지, 길에 해당한지 확인
            // 조건을 모두 만족하면 큐와 방문 배열을 업데이트
            // 시작점에서 해당 위치까지 이동해야 할 칸 수 갱신 (현재 위치보다 한 칸 증가)
            
            for(int i=0; i<4; i++) {
                int x = loc[0] + dx[i];
                int y = loc[1] + dy[i];
  
                if(x >= 0 && y >= 0 && x < row && y < col) {
                    if(graph[x][y] != 0 && visited[x][y] == false) {
                        queue.add(new int[] {x, y});
                        visited[x][y] = true;
                        graph[x][y] = graph[loc[0]][loc[1]] + 1;
                    }
                }
            }
        }
    }
}
