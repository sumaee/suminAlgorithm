import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MOD = 10000003;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[][] dp = new int[n][100001];
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            dp[i][arr[i]] = 1;
            max = Math.max(arr[i], max);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= max; j++) {
                dp[i][j] += dp[i - 1][j] % MOD;

                int gcd = getGcd(arr[i], j);
                dp[i][gcd] += dp[i - 1][j] % MOD;
            }
        }
        System.out.println(dp[n - 1][1] % MOD);
    }

    private static int getGcd(int a, int b) {
        return b != 0 ? getGcd(b, a % b) : a;
    }
}
