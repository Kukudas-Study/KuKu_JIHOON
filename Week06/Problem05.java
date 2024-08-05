// 50.0점

import java.util.*;

class Solution {
    
    static Map<String, List<String>> ticketMap;
    static Map<String, Integer> arriveMap;
    static List<String> ticketList;
    
    public String[] solution(String[][] tickets) {
        
        // 초기화
        ticketMap = new HashMap<>();
        arriveMap = new HashMap<>();
        ticketList = new ArrayList<>();
        
        for (String[] ticket : tickets) {
            if (!ticketMap.containsKey(ticket[0])) {
                ticketMap.put(ticket[0], new ArrayList<>());
            }
            ticketMap.get(ticket[0]).add(ticket[1]);
        }
        
        // 정렬
        for (String key : ticketMap.keySet()) {
            Collections.sort(ticketMap.get(key));
        }
        
        // 모든 공항의 이륙 횟수 초기화
        for (String key : ticketMap.keySet()) {
            arriveMap.put(key, 0);
        }
        
        // DFS 호출
        dfs();
        
        // 결과 반환
        return ticketList.toArray(new String[0]);
    }
    
    static void dfs() {
        Stack<String[]> stack = new Stack<>();
        String secondPort = ticketMap.get("ICN").get(0);
        stack.push(new String[] {"ICN", secondPort});
        ticketList.add("ICN");
        
        while (!stack.isEmpty()) {
            String[] tempTicket = stack.pop();  
            
            // 출발지 이륙횟수 업데이트
            int n1 = arriveMap.get(tempTicket[0]);
            arriveMap.put(tempTicket[0], n1 + 1);
            
            // 도착지 리스트에 추가
            ticketList.add(tempTicket[1]);
            
            // 다음 목적지 확인

            if (ticketMap.containsKey(tempTicket[1])) {
                if(arriveMap.get(tempTicket[1]) < ticketMap.get(tempTicket[1]).size()) {
                    int n2 = arriveMap.get(tempTicket[1]);
                    String nextPort = ticketMap.get(tempTicket[1]).get(n2);
                    stack.push(new String[] {tempTicket[1], nextPort}); 
                }
            }
        }
    }
}
