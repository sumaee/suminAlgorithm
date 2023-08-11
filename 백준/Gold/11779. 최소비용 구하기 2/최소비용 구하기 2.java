import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node>[] bus;
    static int[] dis;
    static int[] preRoute;
    static boolean[] visited;
    static StringBuilder sb;
    static int MAX = 1234567890;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

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

            bus[start].add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        preRoute = new int[n + 1];
        dijkstra(start, end);


        List<Integer> list = new ArrayList<>();
        int curr = end;
        while (curr != 0) {
            list.add(curr);
            curr = preRoute[curr];
        }
        sb.append(list.size()).append("\n");
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i)).append(" ");
        }
        System.out.println(dis[end]);
        System.out.println(sb);
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        que.offer(new Node(start, 0));
        dis[start] = 0;
        preRoute[start] = 0;

        while (!que.isEmpty()) {
            Node curr = que.poll();

            if (curr.city == end) break;

            if (visited[curr.city]) continue;

            visited[curr.city] = true;

            for (Node nxt : bus[curr.city]) {
                if (dis[nxt.city] > dis[curr.city] + nxt.cost) {
                    dis[nxt.city] = dis[curr.city] + nxt.cost;
                    que.offer(new Node(nxt.city, dis[nxt.city]));
                    preRoute[nxt.city] = curr.city;
                }
            }
        }
    }

    static class Node {
        int city, cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }
}


