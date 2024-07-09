// [PRO] 12946. 하노이의 탑

import java.util.*;

class Solution {
    static ArrayList<int[]> list = new ArrayList<>();

    // i개의 원판을 출발지에서 목적지로 옮기는 재귀함수
    static void move(int i, int start, int end) {
             
        if(i==0) {
            return;
        }
        
        int temp = 6-start-end;   
      
        move(i-1, start, temp);             // 1단계 : i-1개의 원판을 출발지에서 임시 기둥으로 옮김
        list.add(new int[]{start, end});    // 2단계 : i번째 원판을 출발지에서 목적지로 옮김 
        move(i-1, temp, end);               // 3단계 : i-1개의 원판을 임시 기둥에서 목적로 옮김
                                            // 재귀 함수가 2번씩 호출되었으므로, 시간복잡도는 O(2^n).
    }
    
    public int[][] solution(int n) {
        move(n, 1, 3);
        int[][] answer = list.stream().toArray(int[][]::new);
        return answer;
    }
    
}
