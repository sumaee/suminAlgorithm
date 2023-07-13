import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
         int[][] town = new int[N + 1][N + 1];

        for (int[] info : town) {
            Arrays.fill(info, Integer.MAX_VALUE);
        }

        for (int[] info : road) {
            int start = info[0];
            int end = info[1];
            int time = info[2];

            if (town[start][end] > time) {
                town[start][end] = time;
                town[end][start] = time;
            }
        }

        int[] times = new int[N + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        return dijkstra(N, K, town, times);
    }

    private static int dijkstra(int N, int K, int[][] town, int[] times) {
        PriorityQueue<Town> que = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        que.offer(new Town(1, 0));
        times[1] = 0;

        while (!que.isEmpty()) {
            Town curr = que.poll();

            for (int i = 1; i <= N; i++) {
                if (times[i] < times[curr.idx] + town[curr.idx][i] || town[curr.idx][i] == Integer.MAX_VALUE) continue;
                times[i] = Math.min(times[i], times[curr.idx] + town[curr.idx][i]);
                que.offer(new Town(i, times[i]));
            }
        }

        int answer = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] <= K) answer++;
        }

        return answer;
    }

    static class Town {
        int idx, time;

        public Town(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
