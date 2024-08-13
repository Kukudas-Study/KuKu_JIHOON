import java.io.*;
import java.util.*;

public class Main {

    // 변수 선언
    static int N, M;

    // 방향 배열 (우, 좌, 하, 상)
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    // 미로 배열 및 방문 배열
    static int[][] graph;
    static boolean[][] visited;

    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        // 초기화
        br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 줄 입력 처리 (N: 행, M: 열)
        String[] A = br.readLine().split(" ");
        N = Integer.parseInt(A[0]);
        M = Integer.parseInt(A[1]);

        // 미로 배열과 방문 배열 초기화
        graph = new int[N][M];
        visited = new boolean[N][M];

        // 미로 데이터 입력
        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = S.charAt(j) - '0';
            }
        }

        // BFS 탐색 시작
        bfs();
    }

    // BFS 탐색 함수
    static void bfs() {
        // BFS를 위한 큐 선언 (x, y 좌표, 현재 이동 거리)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});  // 시작 지점 추가

        while (!queue.isEmpty()) {
            int[] L = queue.poll();  // 큐에서 좌표 꺼내기

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int x = L[0] + dx[i];
                int y = L[1] + dy[i];
                
                // 유효한 좌표인지 확인
                if (x >= 0 && x < N && y >= 0 && y < M) {
                    // 방문하지 않았고, 벽이 아닌 경우
                    if (!visited[x][y] && graph[x][y] == 1) {
                        queue.add(new int[]{x, y, L[2] + 1});  // 다음 위치 큐에 추가
                        visited[x][y] = true;  // 방문 표시
                        
                        // 도착지점에 도달한 경우
                        if (x == N - 1 && y == M - 1) {
                            System.out.print(L[2] + 1);  // 결과 출력
                            return;  // 탐색 종료
                        }
                    }
                }
            }
        }
    }
}
