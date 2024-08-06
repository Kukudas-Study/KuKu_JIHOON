import java.util.*;
import java.io.*;

public class Main {
  
    // 스도쿠 숫자들을 저장할 2차원 배열
    static int[][] sudoku = new int[9][9];
    
    // 빈 칸의 좌표를 저장할 리스트
    static List<int[]> zeroList = new ArrayList<>();
  
    // 스도쿠를 모두 풀었는지?
    static boolean solve;

    public static void main(String[] args) throws Exception {
      
        // 준비 단계
      
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
          
        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                // 스도쿠 2차원 배열 초기화
                sudoku[i][j] = Integer.parseInt(st.nextToken());

                // 빈 칸(0)이면 해당 좌표를 zeroList에 추가
                if(sudoku[i][j] == 0) zeroList.add(new int[] {i, j});
            }
        }
  
        // dfs 탐색 단계
	    
        dfs(0);
  
        // 출력 단계
      
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                bw.write(sudoku[i][j] + " ");
            }
	    bw.newLine();
        }
          
        bw.flush();
        bw.close();
        br.close();
    }
	
    static void dfs(int level) throws Exception {
        // 모든 빈 칸이 채워졌을 때 종료
        if(level == zeroList.size()) {
            solve = true;
            return;
	    }

        // 현재 빈 칸의 좌표를 가져옴
    	int x = zeroList.get(level)[0];
    	int y = zeroList.get(level)[1];
      
    	// 현재 빈 칸에 1부터 9까지 모두 넣어봄
        // 유효하면 다음 빈 칸으로 이동함
        // 모두 해결되면 즉시 종료함.
        // 유효하지 않으면 백트래킹
    	for(int num = 1; num <= 9; num++) {
		    if(check(x, y, num)) { 
			    sudoku[x][y] = num; 
        		dfs(level+1);
        		if(solve) return;
        		sudoku[x][y] = 0; 
			}	
		}
  	}
	
	static boolean check(int x, int y, int num) {
        // 가로줄에 같은 숫자가 있는지 검사
		for(int i = 0; i < 9; i++) {
	        if(sudoku[x][i] == num) return false;
	    }

        // 세로줄에 같은 숫자가 있는지 검사
  	    for(int j = 0; j < 9; j++) {
  	        if(sudoku[j][y] == num) return false;
  	    }

        // 3x3 박스 같은 숫자가 있는지 검사
	    for(int i = 0; i < 3; i++) {
	        for(int j = 0; j < 3; j++) {
	            if(sudoku[x/3 * 3 + i][y/3 * 3 + j] == num) return false;
	        }
	    }

        // 모두 중복되지 않으면 true 반환
	    return true;
    }
}
