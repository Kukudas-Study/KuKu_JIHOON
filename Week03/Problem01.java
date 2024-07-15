import java.io.*;
import java.util.*;

public class Main {
    
    static ArrayList<Integer>[] graph; 
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;

    public static void main(String[] args) throws IOException, NumberFormatException {

        // 입력 준비
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());  // 노드 개수
        int M = Integer.parseInt(st.nextToken());  // 에지 개수
        int V = Integer.parseInt(st.nextToken());  // 시작 노드
        
        // 그래프 배열과 방문 배열 초기화
      
        dfsVisited = new boolean[N + 1];  
        bfsVisited = new boolean[N + 1];
        graph = new ArrayList[N + 1]; 
        
        for (int i = 1; i <= N; i++) {  
            graph[i] = new ArrayList<>();
        }

        // 노드별 에지 입력

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        System.out.println(dfs(N, V));
        System.out.println(bfs(N, V));
    }

  
    public static String dfs(int N, int V) {
      
        // dfs 탐색을 위한 스택을 생성합니다.
        // 스택에 시작 노드를 넣습니다.
        // 경로가 담길 스트링버퍼를 선언합니다.
      
        Stack<Integer> stack = new Stack<>();
        stack.push(V);
        StringBuffer sb = new StringBuffer();

        // 그래프 노드별 에지 정렬 (내림차순)
      
        for (int i = 1; i <= N; i++) {   
            Collections.sort(graph[i], Comparator.reverseOrder());
        }

        // 스택이 비어있지 않다면 맨 위의 요소를 추출합니다. (LIFO)
        // 해당 요소가 방문한 적이 없다면 방문 배열과 스트링버퍼를 갱신합니다.
        // 해당 요소와 이어진 노드들 중에 방문한 적이 없는 노드를 스택에 넣습니다.
        // 스택이 빌 때까지 루프를 반복합니다.
      
        while (!stack.isEmpty()) {
            int currentNode = stack.pop();
            
            if (!dfsVisited[currentNode]) {
                dfsVisited[currentNode] = true;
                sb.append(currentNode).append(" ");
                for (int node : graph[currentNode]) {
                    if (!dfsVisited[node]) stack.push(node);
                }
            }
        }
        
        return sb.toString();
    }
    
    public static String bfs(int N, int V) {

        // bfs 탐색을 위한 큐를 생성합니다.
        // 큐에 시작 노드를 추가합니다.
        // 경로가 담길 스트링버퍼를 선언합니다.
      
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(V);
        StringBuffer sb = new StringBuffer();

        // 그래프 노드별 에지 정렬 (오름차순)
      
        for (int i = 1; i <= N; i++) {   
            Collections.sort(graph[i]);
        }

        // 큐가 비어있지 않다면 맨 앞의 요소를 추출합니다. (FIFO)
        // 해당 요소가 방문한 적이 없다면 방문 배열과 스트링버퍼를 갱신합니다.
        // 해당 요소와 이어진 노드들 중에 방문한 적이 없는 노드를 큐에 넣습니다.
        // 큐가 빌 때까지 루프를 반복합니다. 
        
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            
            if (!bfsVisited[currentNode]) {
                bfsVisited[currentNode] = true;
                sb.append(currentNode).append(" ");
                
                for (int node : graph[currentNode]) {
                    if (!bfsVisited[node]) queue.offer(node);
                }
            }
        }
        
        return sb.toString();
    }
}
