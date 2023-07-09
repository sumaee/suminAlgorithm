import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];
        dp[0] = 1;

        for (int j = 1; j <= n; j *= 2) {
            for (int i = 1; i <= n; i++) {
                if (i < j) continue;
                dp[i] += dp[i - j] % 1000000000;
            }
        }

        System.out.println(dp[n] % 1000000000);
    }
}
