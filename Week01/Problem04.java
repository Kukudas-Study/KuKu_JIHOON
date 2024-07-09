import java.util.*;
import java.util.stream.Collectors;

// [PRO] 12977. 소수 만들기

class Solution {
    public int solution(int[] nums) {
        
        int count = 0; 
        List<Integer> sumList = new ArrayList<>();

        // nums에 있는 숫자들 중 서로 다른 3개의 합을 구함.
        
        for(int i=0; i<nums.length-2; i++) {
            for(int j=i+1; j<nums.length-1; j++) {
                for(int k=j+1; k<nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    sumList.add(sum);
                }
            }
        }

        // 최대합 이하의 소수들을 배열 P에 담음.
        
        Integer max = Collections.max(sumList);
        int[] primeArr = prime(max);

        // 탐색시간을 줄이기 위해 HashSet으로 변환함.
        
        Set<Integer> primeSet = new HashSet<>();
        for (int prime : primeArr) {
            primeSet.add(prime);
        }

        // HashSet에 합이 존재하는지 탐색함.
        
        for(int sum : sumList) {
            if (primeSet.contains(sum)) {
                count++;
            }
        }
        
        return count;
    }

    
    // N 이하의 소수들이 담긴 배열을 리턴함. 
    
    public static int[] prime(int N) {
        
        int[] arr = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 2; i * i <= N; i++) {
            if (arr[i] == 0) continue;

            for (int j = i; j * i <= N; j++) {
                arr[j * i] = 0;
            }
        }
        
        arr = Arrays.stream(arr)
                .filter(n -> (n != 0))
                .toArray();

        return arr;
    }

    
}
