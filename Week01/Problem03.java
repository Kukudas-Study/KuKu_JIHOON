// [PRO] 42583. 다리를 지나는 트럭

class Solution {
    public int solution(int bridge_length, int bridge_weight, int[] truck_weights) {

        // 변수 초기화
        
        int time = 0;                    // 현재 시간
        int sum = 0;                     // 현재 다리의 무게  
        int T = truck_weights.length;    // 전체 트럭의 개수
        
        // 트럭 배열 초기화
        
        Truck[] trucks = new Truck[T]; 
        
        for(int i=0; i<trucks.length; i++) {
            trucks[i] = new Truck(truck_weights[i]);
        }
        
        trucks[0].end = bridge_length;

        // 투 포인터 
        
        int i=0;   // 현재 다리에서 나가려는 트럭 번호
        int j=0;   // 현재 다리에 진입하려는 트럭 번호
        
        while(i < T) {
            
            if(time == trucks[i].end) {
                sum -= trucks[i++].weight;
            }
            
            if((j < T) && (sum + trucks[j].weight <= bridge_weight)) {
                trucks[j].start = time;
                trucks[j].end = time + bridge_length;
                sum += trucks[j++].weight;
            }
            time++;
        }
        
        return time;
    }

    
    class Truck {
        int weight = 0;   // 트럭의 무게
        int start = 0;    // 트럭이 다리에 진입하는 시점
        int end = 0;      // 트럭이 다리에서 나가는 시점
        
        Truck(int weight) {
            this.weight = weight;
        }
    }

}
