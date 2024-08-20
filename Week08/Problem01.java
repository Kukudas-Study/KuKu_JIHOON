import java.io.*;
import java.util.*;

public class Main {

    static int N = 0;  // 공간의 크기
    static int M = 2;  // 상어의 크기
    static int J = 0;  // 현재 상어가 먹을 수 있는 물고기의 수

    static int x, y;   // 상어의 현재 좌표표
    static int d;      // 상어의 이동 거리

    static int[][] A;        // 공간 배열
    static List<int[]>[] B;  // 크기별 물고기 위치 리스트
    static List<int[]> T;    // 상어보다 작은 물고기 위치 리스트

    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N][N];
        B = new ArrayList[7];
        T = new ArrayList<>();
      
        for (int i = 0; i < 7; i++) {
            B[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());

                if (A[i][j] == 0) continue; // 빈 공간이면 넘어감
                
                if (A[i][j] == 9) { 
                    A[i][j] = 0;  // 상어가 있는 위치를 빈 공간으로 설정
                    x = i;        // 상어의 x 좌표 설정
                    y = j;        // 상어의 y 좌표 설정
                    continue;
                }

                // 물고기의 크기별로 위치 저장
                B[A[i][j]].add(new int[]{i, j});
            }
        }

        // 이동 시작 
        Label_outer:
        while (true) {
            // 상어가 성장할 때마다 새로운 타겟을 추가함
            if (M < 8) setFish();

            // M인 상어는 물고기 M마리를 먹으면 성장함 
            // 먹을 수 있는 물고기가 없으면 종료
            for (int i = 0; i < M; i++) {
                if (!eatTarget()) break Label_outer; 
            }

            // 상어의 크기 증가
            M++;
        }

        // 상어가 이동한 거리 출력
        System.out.println(d);
    }

  
    static void setFish() {
        // 상어가 M이 되면 M-1인 물고기도 먹을 수 있음. 
        // 새로운 M-1 타겟들의 숫자를 추가
        // 새로운 M-1 타겟들의 위치를 추가
        J += B[M - 1].size();
        T.addAll(B[M - 1]);
    }

  
    static boolean eatTarget() {
        
        int[] target = new int[2];        // 현재 타겟 위치
        int minPath = Integer.MAX_VALUE;  // 최소 경로 길이

        // 아직 먹지 못한 물고기 리스트를 순회하여 각 물고기마다 bfs로 최소 거리를 측정함 
        // 최소 거리가 가장 짧은 물고기가 현재 타겟임.
        for (int[] fish : T) {
            int path = bfs(fish);        
            if (path > minPath) continue;  // 가장 가까운 물고기가 아니라면 무시

            if (path < minPath) {
                minPath = path;
                target = fish;
                continue;
            }

            // 현재 타겟과 새로운 타겟이 거리가 같으면, 상단에 위치한 물고기를 우선 선택
            if (path == minPath && fish[0] < target[0]) {
                minPath = path;
                target = fish;
                continue;
            }

            // 현재 타겟과 새로운 타겟이 거리가 같고 행이 같으면, 좌측에 위치한 물고기를 우선 선택
            if (path == minPath && fish[0] == target[0] && fish[1] < target[1]) {
                minPath = path;
                target = fish;
                continue;
            }
        }

        // 한번이라도 최소 경로를 갱신했으면
        // 상어의 위치를 현재 타겟의 위치로 이동시키고 거리를 최소 경로만큼 더함
        if (minPath < Integer.MAX_VALUE) {
            x = target[0];  
            y = target[1];
            d += minPath;

            // 먹은 물고기를 리스트에서 제거
            // 먹을 수 있는 물고기 수 감소
            for (int i = 0; i < T.size(); i++) {
                if (target[0] == T.get(i)[0] && target[1] == T.get(i)[1]) {
                    T.remove(i);
                    J--;  
                    break;
                }
            }

            return true; // 물고기를 먹었다고 알려줌
        }

        return false; // 물고기를 못 먹었다고 알려줌
    }

    // BFS를 이용해 특정 물고기까지의 최소 경로를 계산하는 함수
    static int bfs(int[] dest) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, 0});

        boolean[][] V = new boolean[N][N];  // 방문 배열
        V[x][y] = true;                     // 현재 위치 방문

        while (!queue.isEmpty()) {
            int[] p = queue.poll();  // 큐에서 현재 위치를 꺼냄

            // 상하좌우로 이동하며 BFS 수행
            for (int i = 0; i < 4; i++) {
                int a = p[0] + dx[i];
                int b = p[1] + dy[i];

                // 공간을 벗어난 경우 무시
                if (a < 0 || b < 0 || a >= N || b >= N) continue;

                // 방문하지 않았고, 상어의 크기 이하일 경우 이동 가능
                if (V[a][b] == false && A[a][b] <= M) {
                    queue.add(new int[]{a, b, p[2] + 1});
                    V[a][b] = true;

                    // 목표 위치에 도달한 경우 경로 길이를 반환
                    if (a == dest[0] && b == dest[1]) {
                        return p[2] + 1;
                    }
                }
            }
        }

        return Integer.MAX_VALUE; // 도달할 수 없는 경우 최대값 반환
    }
}
