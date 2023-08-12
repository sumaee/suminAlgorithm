import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] items;
    static int n, m;
    static List<Road>[] roadList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 지역 개수
        m = Integer.parseInt(st.nextToken()); // 수색범위
        int r = Integer.parseInt(st.nextToken()); // 길의 개수

        //아이템수 저장
        st = new StringTokenizer(br.readLine());
        items = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        //지역 사이의 거리 저장
        roadList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            roadList[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            roadList[start].add(new Road(end, len));
            roadList[end].add(new Road(start, len));
        }

        //각 위치에 떨어졌을 때 갈 수 있는 범위 탐색
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int result = dijkstra(i);
            answer = Math.max(answer, result);
        }
        System.out.println(answer);
    }

    private static int dijkstra(int start) {
        PriorityQueue<Road> que = new PriorityQueue<Road>((o1, o2) -> o1.len - o2.len);
        que.offer(new Road(start, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, 987654321);
        dist[start] = 0;

        while (!que.isEmpty()) {
            Road curr = que.poll();

            for (Road nxt : roadList[curr.idx]) {
                if (dist[nxt.idx] > dist[curr.idx] + nxt.len) {
                    dist[nxt.idx] = dist[curr.idx] + nxt.len;
                    que.offer(new Road(nxt.idx, dist[nxt.idx]));
                }
            }
        }

        //지정된 수색 범위 내로 갈수 있는 곳만 아이템 개수 세기
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) {
                cnt += items[i];
            }
        }
        return cnt;
    }

    static class Road {
        int idx, len;

        public Road(int idx, int len) {
            this.idx = idx;
            this.len = len;
        }
    }

}
