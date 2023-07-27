import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        matrix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i] = Integer.parseInt(st.nextToken());
            matrix[i + 1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int answer = dfs(0, n - 1);
        System.out.println(answer);
    }

    private static int dfs(int idx, int nxtIdx) {
        if (idx == nxtIdx) return 0;
        if (dp[idx][nxtIdx] != Integer.MAX_VALUE) return dp[idx][nxtIdx];

        for (int i = idx; i < nxtIdx; i++) {
            int result = dfs(idx, i) + dfs(i + 1, nxtIdx) + (matrix[idx] * matrix[i + 1] * matrix[nxtIdx + 1]);
            dp[idx][nxtIdx] = Math.min(dp[idx][nxtIdx], result);
        }

        return dp[idx][nxtIdx];
    }

    static class Matrix {
        int row, col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
