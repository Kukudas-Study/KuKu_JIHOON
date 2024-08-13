import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] A;

    // 방향 배열 (상, 우상, 우, 우하, 하, 좌하, 좌, 좌상)
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static BufferedReader br;
    static BufferedWriter bw;

    // 파이어볼을 저장할 2차원 리스트
    static List<Ball>[][] B;
    // 파이어볼 위치(리스트)를 저장할 집합
    static Set<List<Integer>> S;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = input();
        N = A[0];   // 격자 크기
        M = A[1];   // 파이어볼 개수
        K = A[2];   // 이동 횟수

        B = new ArrayList[N][N];
        S = new HashSet<>();

        // 파이어볼 초기 정보 입력
        for (int i = 0; i < M; i++) {
            A = input();
            // 파이어볼을 배열의 특정 위치에 있는 리스트에 저장함
            // 만일 특정 위치에서 리스트가 없다면 새로 초기화함
            if (B[A[0] - 1][A[1] - 1] == null) {
                B[A[0] - 1][A[1] - 1] = new ArrayList<Ball>();
            }
            B[A[0] - 1][A[1] - 1].add(new Ball(A[2], A[3], A[4]));
            
            // 파이어볼이 있는 위치를 집합에 저장함
            S.add(Arrays.asList(A[0] - 1, A[1] - 1));
        }

        // K번 이동
        for (int i = 1; i <= K; i++) {
            move();
        }

        int answer = 0;

        // 남아있는 모든 파이어볼의 질량 합을 계산함
        for (List<Integer> L : S) {
            List<Ball> list = B[L.get(0)][L.get(1)];
            for (Ball ball : list) {
                answer += ball.mass;
            }
        }

        // 결과 출력
        System.out.println(answer);
    }

  
    // 입력 함수
    static int[] input() throws IOException {
        return Arrays.stream(br.readLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

  
    // 파이어볼 이동 및 합체/분열
    static void move() {
        // 새로운 배열과 집합을 선언
        List<Ball>[][] newB = new ArrayList[N][N];
        Set<List<Integer>> newS = new HashSet<>();

        // 파이어볼 이동
        // 집합에 저장된 위치에서만
        for (List<Integer> L : S) {
            // 현재 위치 확인
            List<Ball> list = B[L.get(0)][L.get(1)];
          
            for (Ball ball : list) {
                // 기존 배열의 현재 위치에 있는 리스트에서 파이어볼들을 확인
                // 현재 볼의 새로운 위치 확인
                int x = (L.get(0) + dx[ball.direction] * ball.speed) % N;
                int y = (L.get(1) + dy[ball.direction] * ball.speed) % N;
                if (x < 0) x += N;
                if (y < 0) y += N;
              
                // 새로운 배열의 새로운 위치에 있는 리스트에 파이어볼 추가
                // 새로운 위치를 새로운 위치 집합에 저장
                if (newB[x][y] == null) {
                    newB[x][y] = new ArrayList<Ball>();
                }
                newB[x][y].add(new Ball(ball.mass, ball.speed, ball.direction));
                newS.add(Arrays.asList(x, y));
            }
        }

        // 파이어볼 합체 및 분열
        for (List<Integer> L : newS) {
            List<Ball> list = newB[L.get(0)][L.get(1)];
            if (list.size() == 1) continue;

            // 한 곳(리스트)에 파이어볼 2개 이상이 있을 때 아래 코드를 수행.
            int totalMass = 0, totalSpeed = 0;
            boolean allOdd = true, allEven = true;

            for (Ball ball : list) {
                totalMass += ball.mass;
                totalSpeed += ball.speed;
                // 방향에 하나라도 짝수가 있으면 allOdd 조건을 만족하지 않음. 반대도 마찬가지.
                if (ball.direction % 2 == 0) allOdd = false;
                else allEven = false;
            }

            // 새로운 파이어볼의 질량과 속도를 결정함.
            int newMass = totalMass / 5;
            int newSpeed = totalSpeed / list.size();
          
            // 기존 위치의 리스트를 초기화함.
            newB[L.get(0)][L.get(1)] = new ArrayList<Ball>();

            // 새로운 파이어볼들의 질량이 0이면 새로운 배열의 기존 위치 리스트에 추가하지 않음.
            if (newMass == 0) continue;

            int i = 0;
            if (!allOdd && !allEven) i = 1;

            // 새로운 파이어볼 4개를 새로운 배열의 기존 위치 리스트에 추가함.
            while (i < 8) {
                newB[L.get(0)][L.get(1)].add(new Ball(newMass, newSpeed, i));
                i += 2;
            }
        }

        // 배열과 위치 집합을 새로운 배열과 새로운 위치 집합으로 업데이트함.
        B = newB;
        S = newS;
    }
}


// 파이어볼 클래스
class Ball {
    int mass, speed, direction;

    Ball(int mass, int speed, int direction) {
        this.mass = mass;
        this.speed = speed;
        this.direction = direction;
    }
}
