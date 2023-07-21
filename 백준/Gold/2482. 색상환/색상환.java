import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mod = 1000000003;

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long[][] dp = new long[n + 1][k + 1];

        if (k > n / 2) {
            System.out.println(0);
        } else {
            for (int i = 1; i <= n; i++) {
                dp[i][1] = i;
            }

            for (int i = 4; i <= n; i++) {
                for (int j = 2; j <= k; j++) {
                    dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % mod;
                }
            }
            System.out.println(dp[n][k]);
        }


    }
}
