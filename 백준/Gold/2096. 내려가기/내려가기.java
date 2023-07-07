import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Pair[][] dp = new Pair[n][3];
        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (i == 0) {
                    dp[i][j] = new Pair(arr[i][j], arr[i][j]);
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int max, min;
                if (j == 0) {
                    max = Math.max(dp[i - 1][0].max, dp[i - 1][1].max);
                    min = Math.min(dp[i - 1][0].min, dp[i - 1][1].min);
                } else if (j == 1) {
                    max = Math.max(Math.max(dp[i - 1][0].max, dp[i - 1][1].max), dp[i - 1][2].max);
                    min = Math.min(Math.min(dp[i - 1][0].min, dp[i - 1][1].min), dp[i - 1][2].min);
                } else {
                    max = Math.max(dp[i - 1][1].max, dp[i - 1][2].max);
                    min = Math.min(dp[i - 1][1].min, dp[i - 1][2].min);
                }

                dp[i][j] = new Pair(max + arr[i][j], min + arr[i][j]);
            }
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(dp[n - 1][i].max, max);
            min = Math.min(dp[n - 1][i].min, min);
        }
        System.out.println(max + " " + min);
    }

    static class Pair {
        int min, max;

        public Pair(int max, int min) {
            this.min = min;
            this.max = max;
        }
    }
}


