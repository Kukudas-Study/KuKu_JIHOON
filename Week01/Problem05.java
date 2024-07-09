import java.util.*;

// [BOJ] 2960. 에라토스테네스의 체

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int K = sc.nextInt();
    
    int count = 0;          // 제거 횟수
    int answer = 0;         // K번째로 제거한 자연수
    boolean stop = false;   // 외부 루프를 그만할까?

    int[] nums = new int[N + 1];

    for (int i = 2; i <= N; i++) {
      nums[i] = i;
    }

    for(int i = 2; i <= N; i++) {
    
      // 이미 소수가 아니면 건너뜀.
      
      if(nums[i] == 0) continue;

      // 소수의 배수들을 전부 0으로 바꿈.
      // count가 K이면 모든 루프에서 탈출함.
      
      for (int j = 1; j * i <= N; j++) {
        if(nums[i*j] != 0) {
          nums[i * j] = 0;
          count++;
        } 
        if(count == K) {
          stop = true;
          answer = i*j;
          break;
        }
      }
      if(stop) break;
    }

    System.out.println(answer);

  }
}
