import java.util.*;

// [BOJ] 22864. 피로도

public class Main {
  public static void main(String[] args) {

    // 입력 단계

    Scanner sc = new Scanner(System.in);
    int A = sc.nextInt(); // 근무 시간당 피로도 (+)
    int B = sc.nextInt(); // 근무 시간당 작업량
    int C = sc.nextInt(); // 휴식 시간당 피로도 (-)
    int M = sc.nextInt(); // 최대 피로도

    int p = 0; // 현재 피로도
    int w = 0; // 현재 작업량

    for (int i = 0; i < 24; i++) {
      // 피로도가 적으면 근무
      if (A + p <= M) {
        p += A;
        w += B;
      }

      // 피로도가 많으면 휴식
      else {
        p -= C;
        if (p < 0)
          p = 0;
      }
    }

    System.out.print(w);

  }
    
}
