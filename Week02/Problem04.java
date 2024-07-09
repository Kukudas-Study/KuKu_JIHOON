// [PRO] 42885. 구명보트

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        // 오름차순 정렬
        Arrays.sort(people);

        // 투 포인터
        int i=people.length-1;
        int j=0;
        int count = 0;
        
        while(i >= j) {
            // 아직 탈출하지 않은 사람들 중 최대와 최소의 합을 구함.
            // 만일 합이 limit 이하이면 최대와 최소를 같이 태움.
            // 만일 합이 limit보다 크면 최대만 구명 보트에 태움.
            // 모든 사람이 탈출할 때까지 루프를 반복함.
            if(people[i] + people[j] <= limit) {
                i--;
                j++;
                count++;
            } else {
                i--;
                count++;
            }
                  
        }
    
        return count;
    }
}
