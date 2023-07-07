import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        if (n == 1) {
            System.out.println(0);
            return;
        }
        dp[2] = 3;
        int total = 0;

        for (int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * 3 + (total * 2 + 2));
            total += dp[i - 2];
        }

        System.out.println(dp[n]);
    }
}
