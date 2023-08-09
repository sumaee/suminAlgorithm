import java.util.*;

class Solution {
    static final int INF = 987654321;
    static List<Integer>[] lists;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        lists = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            lists[road[0]].add(road[1]);
            lists[road[1]].add(road[0]);
        }

        int[] answer = new int[sources.length];
        int[] result = dijkstra(destination, n);

        for (int i = 0; i < sources.length; i++) {
            answer[i] = result[sources[i]] == INF ? -1 : result[sources[i]];
        }
        return answer;
    }

    private static int[] dijkstra(int destination, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Info> que = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        que.offer(new Info(destination, 0));
        dist[destination] = 0;

        while (!que.isEmpty()) {
            Info curr = que.poll();

            for (int nxt : lists[curr.idx]) {
                if (dist[nxt] > curr.time + 1) {
                    dist[nxt] = curr.time + 1;

                    que.offer(new Info(nxt, dist[nxt]));
                }
            }
        }

        return dist;
    }

    static class Info {
        int idx, time;

        public Info(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
