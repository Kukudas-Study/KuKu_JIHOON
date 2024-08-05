import java.util.*;
import java.io.*;

public class Main {

  public static int[][] team;
  public static boolean[] visited;
  public static int sum, max;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());  // 테스트횟수

    for(int t=0; t<T; t++) {
      // 테스트마다 팀 스탯을 초기화함
      team = new int[11][11];
      for(int i=0; i<11; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<11; j++) {
          team[i][j] = Integer.parseInt(st.nextToken());  // 팀 능력치 입력
        }
      }

      sum = 0;
      max = 0;
      visited = new boolean[11];

      dfs(0);

      System.out.println(max);

    }

    br.close();
  }

  public static void dfs(int depth) {
    // depth는 현재 분석 중인 선수의 인덱스
    // depth가 11이면 모든 선수를 분석함.
    if(depth == 11) {
      max = Math.max(max, sum);
      return;
    }

    // 선수별로 모든 포지션을 분석함
    for(int i=0; i<11; i++) {
      // 만일 해당 포지션을 할 수 없으면 건너뜀.
      // 이미 해당 포지션을 분석했으면 건너뜀.
      if(team[depth][i] != 0 && visited[i] == false) {
        // 선수를 해당 포지션에 배치해봄. sum도 업데이트함.
        // 이때 다음 선수를 분석함. (백트래킹)
        // 재귀호출이 종료되면 선수를 포지션에서 뺌. sum도 초기화함.
        sum += team[depth][i];
        visited[i] = true;
        dfs(depth+1);
        sum -= team[depth][i];
        visited[i] = false;
      }
    }
  }

}
