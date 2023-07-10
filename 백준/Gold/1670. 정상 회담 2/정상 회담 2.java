import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];
        long mod = 987654321;
        if (n == 0) {
            System.out.println(1);
        } else if (n == 2) {
            System.out.println(1);
        } else {
            dp[0] = 1;
            dp[2] = 1;

            for (int i = 4; i <= n; i += 2) {
                for (int j = 0; j <= i - 2; j += 2) {
                    dp[i] += (dp[j] * dp[i - j - 2]);
                    dp[i] %= mod;
                }
            }

            System.out.println(dp[n] % mod);
        }
    }
}
