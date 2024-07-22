import java.util.*;

class Solution {
    
    static int row, col, x, y;
    static int[] start, lever, end;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    static boolean[][] visited;
    static String[] graph;
    
    public int solution(String[] maps) {

        row = maps.length;
        col = maps[0].length();

        // 그래프를 초기화하고, start, lever, end의 위치를 찾는다.
        
        graph = maps;
        setLocation();

        // start에서 lever로 이동하는 경로를 탐색한다.
        // 경로가 없다면 탐색을 중단한다.
      
        int d1 = bfs(start, lever);
        if(d1 == -1) return -1;

        // lever에서 end로 이동하는 경로를 탐색한다.
        // 경로가 없다면 -1을 반환한다.
        
        int d2 = bfs(lever, end);
        if(d2 == -1) return -1;

        // 두 경로의 길이를 합산힌디.
      
        return d1 + d2;
    }
    
    
    // 반복문을 통해 시작, 레버, 종료 위치 찾기
    
    static void setLocation() {
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(graph[i].charAt(j) == 'S') {
                    start = new int[] {i, j};
                } 
                if(graph[i].charAt(j) == 'L') {
                    lever = new int[] {i, j};
                }
                if(graph[i].charAt(j) == 'E') {
                    end = new int[] {i, j};
                }
            }
        }
    }
    
    
    static int bfs(int[] source, int[] destination) {
        // {현재 행, 현재 열, 현재 위치까지의 거리} 형태로 큐에 저장한다.
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {source[0], source[1], 0});
        
        visited = new boolean[row][col];
        visited[source[0]][source[1]] = true;
        
        while(!queue.isEmpty()) {
            int[] loc = queue.poll();
            
            if(loc[0] == destination[0] && loc[1] == destination[1]) {
                return loc[2];
            }
            
            for(int i=0; i<4; i++) {
                int x = loc[0] + dx[i];
                int y = loc[1] + dy[i];
                if(x >= 0 && y >= 0 && x < row && y < col) {
                    if(visited[x][y] == false && graph[x].charAt(y) != 'X') {
                        queue.offer(new int[] {x, y, loc[2]+1});
                        visited[x][y] = true;
                    }
                }
            }
        }
        
        return -1;
    }
}
