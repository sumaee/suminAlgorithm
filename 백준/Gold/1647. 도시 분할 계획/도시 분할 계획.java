import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static List<Road1>[] road;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        road = new List[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            road[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            road[start].add(new Road1(end, cost));
            road[end].add(new Road1(start, cost));
        }

        System.out.println(prim(0));
    }

    private static int prim(int start) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        PriorityQueue<Road1> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        que.offer(new Road1(start, 0));
        while (!que.isEmpty()) {
            Road1 curr = que.poll();

            if (visited[curr.idx]) continue;

            visited[curr.idx] = true;
            sum += curr.cost;
            max = Math.max(max, curr.cost);

            for (Road1 nxt : road[curr.idx]) {
                if (!visited[nxt.idx]) {
                    que.offer(new Road1(nxt.idx, nxt.cost));
                }
            }
        }
        return sum - max;
    }
}

class Road1 {
    int idx, cost;

    public Road1(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}