import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int[][] dp = new int[n + 1][m + 1];
        int[] arr = new int[n + 1];
        int[] sum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum[i] = sum[i - 1] + arr[i];
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = -3276800;
            }
        }

        dp[1][1] = arr[1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                dp[i][j] = dp[i - 1][j];

                int num = j == 1 ? -1 : 0;

                for (int k = i - 2; k >= num; k--) {
                    if (k == -1) {
                        dp[i][j] = Math.max(dp[i][j], sum[i]);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + sum[i] - sum[k + 1]);
                    }
                }
            }
        }

        System.out.println(dp[n][m]);

    }
}
