import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int v, e;
    static int[] dis;
    static boolean[] visited;
    static List<Node>[] node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dis = new int[v + 1];
        Arrays.fill(dis, 1234567890);
        visited = new boolean[v + 1];

        int start = Integer.parseInt(br.readLine());
        //초기 설정
        node = new List[v + 1];
        for (int i = 1; i <= v; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            node[x].add(new Node(y, w));
        }

        dijkstra(start);

        for (int i = 1; i <= v; i++) {
            if (dis[i] == 1234567890) {
                sb.append("INF").append("\n");
            } else {
                sb.append(dis[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        //가중치 기준으로 오름차순으로 뽑기
        PriorityQueue<Node> que = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        que.offer(new Node(start, 0));
        dis[start] = 0;

        while (!que.isEmpty()) {
            Node curr = que.poll();

            if (visited[curr.idx]) continue;

            visited[curr.idx] = true;

            //현재 연결된 부분 확인
            for (Node nxt : node[curr.idx]) {
                //만약 방문을 했다면 패스
                if (visited[nxt.idx]) continue;

                //nxt 에 저장된 거리 VS 현재 거리에서 가중치만큼 더한 거리를 확인해 갱신하고 큐에 넣어줌
                dis[nxt.idx] = Math.min(dis[nxt.idx], dis[curr.idx] + nxt.w);
                que.offer(new Node(nxt.idx, dis[nxt.idx]));
            }
        }
    }
}

class Node {
    int idx, w;

    public Node(int idx, int w) {
        this.idx = idx;
        this.w = w;
    }
}
