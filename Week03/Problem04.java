import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, NumberFormatException {

        // 데이터 입력 및 초기화
      
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());   // 땅의 가로 길이
        int N = Integer.parseInt(st.nextToken());   // 땅의 세로 길이
        int B = Integer.parseInt(st.nextToken());   // 인벤토리의 블록 수
        
        Map<Integer, Integer> block = new HashMap<>();  // 높이(key)별 블록의 수(value)를 담는 해시맵
        Map<Integer, Integer> time = new HashMap<>();   // 시간(key)별 최대 높이(value)를 담는 해시맵
        int maxHeight = 0, minHeight = 256, minTime = Integer.MAX_VALUE;

      
        // 블록의 높이를 입력받을 때마다 최대 높이, 최소 높이, 높이별 빈도수를 업데이트함.
      
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int blockHeight = Integer.parseInt(st.nextToken());
                int blockCount = block.getOrDefault(blockHeight, 0) + 1;
                block.put(blockHeight, blockCount);
                maxHeight = Math.max(maxHeight, blockHeight);
                minHeight = Math.min(minHeight, blockHeight);
            }
        }

        // 땅의 높이를 targetHeight(목표치)로 평평하게 만드는 데 걸리는 시간을 전부 조사함.
        // targetHeight(목표치)는 maxHeight(최대 높이)부터 minHeight(최소 높이)까지 순회함.
        // 루프마다 소요 시간과 인벤토리 블록 수를 초기화함.
        for(int targetHeight = maxHeight; targetHeight >= minHeight; targetHeight--) {
            int timeKey = 0;
            int remainingBlocks = B;

            // 블록 맵을 이용하여 높이별로 블록 수가 몇 개 있는지 파악함. 없으면 0개.
            // 현재 높이가 목표치보다 높으면 땅을 제거하고, 인벤토리에 블록을 저장함.
            // 현재 높이가 목표치보다 낮으면 인벤토리에서 블록을 빼고, 땅을 채움.
            // 블록 조정 후 인벤토리에 0개 이상의 블록이 남아있어야 함. (아니면 continue)
            // 타임 맵에서 소요 시간(timeKey)에 해당하는 최대 높이(timeValue)를 갱신함.
            // 최소 소요 시간을 갱신함.
    
            for(int blockHeight = maxHeight; blockHeight >= minHeight; blockHeight--) {
                int blockCount = block.getOrDefault(blockHeight, 0);
                if(blockHeight > targetHeight) {
                    timeKey += 2 * (blockHeight - targetHeight) * blockCount;
                    remainingBlocks += (blockHeight - targetHeight) * blockCount;
                }
                
                if(blockHeight < targetHeight) {
                    timeKey += 1 * (targetHeight - blockHeight) * blockCount;
                    remainingBlocks -= (targetHeight - blockHeight) * blockCount;
                }
            }
            
            if(remainingBlocks < 0) continue;
            int timeValue = time.getOrDefault(timeKey, 0);
            timeValue = Math.max(timeValue, targetHeight);
            time.put(timeKey, timeValue);
            minTime = Math.min(minTime, timeKey);
        }

        // 최소 소요 시간에 해당하는 최대 높이를 출력함. 
        System.out.println(minTime + " " + time.get(minTime));
        br.close();
    }
}
