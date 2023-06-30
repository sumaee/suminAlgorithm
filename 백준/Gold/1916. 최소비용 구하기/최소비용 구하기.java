import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node3>[] bus;
    static int[] dis;
    static boolean[] visited;
    static int MAX = 1234567890;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 도시 개수
        int m = Integer.parseInt(br.readLine()); // 버스 개수

        //초기 설정
        bus = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            bus[i] = new ArrayList<>();
        }

        dis = new int[n + 1];
        Arrays.fill(dis, MAX);
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            bus[start].add(new Node3(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
        System.out.println(dis[end]);
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Node3> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        que.offer(new Node3(start, 0));
        dis[start] = 0;

        while (!que.isEmpty()) {
            Node3 curr = que.poll();

            if (curr.city == end) break;

            if (visited[curr.city]) continue;

            visited[curr.city] = true;

            for (Node3 nxt : bus[curr.city]) {
                if (visited[nxt.city]) continue;

                dis[nxt.city] = Math.min(dis[nxt.city], dis[curr.city] + nxt.cost);
                que.offer(new Node3(nxt.city, dis[nxt.city]));
            }
        }
    }
}

class Node3 {
    int city, cost;

    public Node3(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }
}