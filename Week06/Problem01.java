import java.util.*;

class Solution {
    static int N, count;
    static List<Integer> queenList;  
    
    public int solution(int n) {
        N = n;
        queenList = new ArrayList<>();
        dfs(0);
        return count;
    }
    
    public static void dfs(int depth) {
        // 퀸들을 모두 무사히 배치했으면 경우의 수를 업데이트함.
        if(depth == N) {
            count++;
            return;
        }

        // 각 열에 퀸을 배치함
        for(int x = 0; x < N; x++) {
            if(checkQueen(x, depth)) {                   // 이번 자리에 퀸을 놓을 수 있는지 검사함.
                queenList.add(x);                        // 퀸을 놓아봄
                dfs(depth + 1);                          // 다음 행으로 이동
                queenList.remove(queenList.size() - 1);  // 방금 전에 놓은 퀸을 다시 뺌
            }
        }
    }
    
    public static boolean checkQueen(int x, int y) {
        for(int i = 0; i < y; i++) {
            int queen = queenList.get(i);
            int leftTop = x - (y-i);    // 좌상향 대각선
            int rightTop = x + (y-i);   // 우상향 대각선

            // 다른 퀸이 같은 열에 있거나 대각선에 있는 경우 false 반환
            if(queen == x || queen == leftTop || queen == rightTop) {
                return false;
            }
        }
        
        return true;
    }
}
