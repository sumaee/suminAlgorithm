import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[][] dp;
    static Locate[] cus;
    static int[] dr = {0, -1, 1, 0, 0}; // 고객 위치, 고객위치 기준 상하좌우
    static int[] dc = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        Locate start = new Locate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        cus = new Locate[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            cus[i] = new Locate(row, col);
        }

        dp = new long[n + 1][5];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        long answer = dfs(0, 0, start);
        System.out.println(answer);
    }

    private static long dfs(int idx, int curr, Locate locate) {
        if (idx == n) return 0;

        if (dp[idx][curr] != -1) return dp[idx][curr];

        //고객의 위치까지 가는 경우와 인근까지 가는 경우 최단 거리 찾기
        dp[idx][curr] = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            int drow = cus[idx + 1].row + dr[i];
            int dcol = cus[idx + 1].col + dc[i];

            dp[idx][curr] = Math.min(dp[idx][curr], dfs(idx + 1, i, new Locate(drow, dcol)) + getDis(locate, drow, dcol));
        }

        return dp[idx][curr];
    }

    private static int getDis(Locate curr, int nxtR, int nxtC) {
        return Math.abs(curr.row - nxtR) + Math.abs(curr.col - nxtC);
    }

    static class Locate {
        int row, col;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
