import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dist;
    static boolean[] visited;
    static List<Node6>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        //초기 설정
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];
        dist = new int[n + 1];

        //간선 저장
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[start].add(new Node6(end, c));
            graph[end].add(new Node6(start, c));
        }

        //경유지 정점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 출발지 -> v1 -> v2 -> 도착지
        long result1 = 0;
        result1 += dijkstra(1, v1);
        result1 += dijkstra(v1, v2);
        result1 += dijkstra(v2, n);

        // 출발지 -> v2 -> v1 -> 도착지
        long result2 = 0;
        result2 += dijkstra(1, v2);
        result2 += dijkstra(v2, v1);
        result2 += dijkstra(v1, n);

        //두값을 비교해 더 작은 값 찾기
        long answer = Math.min(result1, result2);

        System.out.println(answer >= Integer.MAX_VALUE ? -1 : answer);

    }

    private static long dijkstra(int start, int end) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        PriorityQueue<Node6> que = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        dist[start] = 0;

        que.offer(new Node6(start, dist[start]));
        while (!que.isEmpty()) {
            Node6 curr = que.poll();

            if (curr.idx == end) break;
            
            if(visited[curr.idx]) continue;
            
            visited[curr.idx] = true;
            for (Node6 nxt : graph[curr.idx]) {
                if (visited[nxt.idx]) continue;
                
                dist[nxt.idx] = Math.min(dist[nxt.idx], dist[curr.idx] + nxt.w);
                que.offer(new Node6(nxt.idx, dist[nxt.idx]));
            }
        }

        return dist[end];
    }
}

class Node6 {
    int idx, w;

    public Node6(int idx, int w) {
        this.idx = idx;
        this.w = w;
    }
}
