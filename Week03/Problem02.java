class Solution {
    public int[] solution(int brown, int yellow) {
        
        int[] answer = {};

        for(int i = 1; i * i <= yellow; i++) {

            // yellow는 노란색 카펫의 넓이입니다.
            // i는 노란색 카펫의 세로 길이입니다.
            // yellow를 i로 나눈 몫은 노란색 카펫의 가로 길이입니다.
            // 넓이는 가로와 세로의 곱이므로, i는 yellow의 약수여야 합니다.
            // 약수가 아니면 노란색 카펫의 세로 길이(i)를 계속 찾습니다.

            if(yellow % i != 0) continue;

            // 전체 카펫의 선분들은 노란색 카펫의 선분들보다 2칸만큼 깁니다.
            
            int width = yellow / i + 2;
            int height = i + 2;

            // 전체 카펫의 넓이가 brown + yellow와 같은지 비교합니다. 
            // 두 값이 같다면 전체 카펫의 width와 height를 반환합니다.
            // 같지 않다면 노란색 카펫의 세로 길이를 계속 찾습니다.

            if(width * height == brown + yellow) {
                answer = new int[] {width, height};
                break;
            }
        } 
            
        return answer;
    }
}
