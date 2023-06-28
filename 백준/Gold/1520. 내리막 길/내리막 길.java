import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] arr;
    static int[][] dp;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m][n];
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1;
        bfs(0, 0);
        System.out.println(dp[m - 1][n - 1]);
    }

    private static void bfs(int row, int col) {
        //겹치는 길이 있을 수 있기 때문에 큐에 들어온 값중 제일 높은 산을 먼저 측정하기 위한 우선순위 큐
        PriorityQueue<Mount> que = new PriorityQueue<>((o1, o2) -> o2.height - o1.height);
        que.offer(new Mount(row, col, arr[row][col]));
        while (!que.isEmpty()) {
            Mount curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 현재보다 높다면 패스
                if (drow < 0 || drow >= m || dcol < 0 || dcol >= n || arr[curr.row][curr.col] <= arr[drow][dcol])
                    continue;

                //방문을 안했던 지점이라면 큐에 넣어주기
                if (dp[drow][dcol] == 0) {
                    que.offer(new Mount(drow, dcol, arr[drow][dcol]));
                }
                dp[drow][dcol] += dp[curr.row][curr.col];
            }
        }
    }
}

class Mount {
    int row, col, height;

    public Mount(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
    }
}