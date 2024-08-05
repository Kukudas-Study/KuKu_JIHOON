class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int width = 0;
        int height = 0;

        // 모든 명함을 순회
        for(int i=0; i<sizes.length; i++) {
            // 긴 변을 n1에, 짧은 변을 n2에 할당
            int n1 = Math.max(sizes[i][0], sizes[i][1]);
            int n2 = Math.min(sizes[i][0], sizes[i][1]);

            // 최대 가로,세로 길이와 비교하고, 더 큰 값으로 업데이트
            width = Math.max(n1, width);
            height = Math.max(n2, height);
        }
        
        return width * height;
    }
}
