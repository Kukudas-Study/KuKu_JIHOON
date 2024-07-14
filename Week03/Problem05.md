## (1) DFS를 이용한 방법

<br>   

```java
class Solution {

    static int count = 0;
    static int T;
    static int[] N;
    
    static void dfs(int sum, int i) {

        // 배열의 마지막 요소까지 탐색하였다면
        // 누적합이 target과 같은지 비교합니다.
        // 같으면 count를 하나 증가시킵니다.
        // 비교 결과와 상관없이 재귀 호출을 종료합니다.
 
        if(i == N.length) {
            if(sum == T) count++;
            return;
        }

        // 아직 배열을 탐색 중이라면
        // 합에 현재 요소를 더한 경우를 재귀적으로 탐색하거나
        // 합에 현재 요소를 뺀 경우를 재귀적으로 탐색합니다.
        
        dfs(sum + N[i], i+1);
        dfs(sum - N[i], i+1);

    }
   
    public int solution(int[] numbers, int target) {
        N = numbers;
        T = target;
        
        dfs(0, 0);
        
        return count;
    }

}
```

<br>   
<br>   
<br>   
<br>   
<br>   

## (2) BFS를 이용한 방법

<Br>   

```java
import java.util.*;

public class Solution {
   
    public int solution(int[] numbers, int target) {

        // BFS 탐색을 위한 Num 타입 큐를 선언합니다.

        Queue<Num> queue = new LinkedList<>();

        // 초기값으로 index 0, sum 0인 Num 객체를 큐에 추가합니다.

        queue.add(new Num(0, 0));

        int count = 0;
        
        while(!queue.isEmpty()) {

            // 큐에서 Num 객체를 하나 꺼냅니다. 
            
            Num num = queue.poll();

            // Num 객체의 index 값이 배열의 길이와 같다면
            // 배열의 끝까지 탐색하였다고 판단합니다.
            // 이때 Num 객체의 sum 값이 타겟과 같은지 비교하고,
            // 둘이 같다면 count를 하나 증가시킵니다.
            // 비교 결과와 상관없이 다음 루프로 건너뜁니다.

            if(num.index == numbers.length) {
                if(num.sum == target) count++;
                continue;
            }

            // 탐색이 진행 중이라면 배열에서 현재 인덱스의 값을 가져옵니다.
            // 해당 값을 더했을 때의 Num 객체를 큐에 추가하고,
            // 해당 값을 뺐을 때의 Num 객체를 큐에 추가합니다.

            int value = numbers[num.index];
            
            queue.add(new Num(num.index+1, num.sum + value));
            queue.add(new Num(num.index+1, num.sum - value));
        }

        // 큐가 완전히 비어있다면 count를 반환합니다.
        
        return count;
    }

}


class Num {
    int index;  // 현재 인덱스
    int sum;    // 누적 합

    Num(int index, int sum) {
        this.index = index;
        this.sum = sum;
    }
}
```
