import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static List<Node>[] travel;
    static int[][] dp;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        travel = new List[n + 1];
        dp = new int[n + 1][m + 1]; // n번 도시를 도착했을 때 m번째 이동함
        for (int i = 1; i <= n; i++) {
            travel[i] = new ArrayList<>();
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            if (start > end) continue;
            travel[start].add(new Node(end, score));
        }

        ans = Integer.MIN_VALUE;
        bfs(1);
        System.out.println(ans);
    }

    private static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);

        int cnt = 1;
        while (!que.isEmpty() && cnt < m) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                int curr = que.poll();
                for (Node nxt : travel[curr]) {
                    if (dp[nxt.idx][cnt + 1] < dp[curr][cnt] + nxt.score) {
                        dp[nxt.idx][cnt + 1] = dp[curr][cnt] + nxt.score;
                        que.offer(nxt.idx);
                    }
                }
            }
            cnt++;
        }

        for (int i = 1; i <= m; i++) {
            ans = Math.max(ans, dp[n][i]);
        }

    }

    static class Node {
        int idx, score;

        public Node(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }
    }
}
