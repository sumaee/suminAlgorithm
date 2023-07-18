import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[] coins = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int k = Integer.parseInt(br.readLine());

            long[] dp = new long[k + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++) {
                int coin = coins[i];
                for (int j = coin; j <= k; j++) {
                    dp[j] += dp[j - coin];
                }
            }
            sb.append(dp[k]).append("\n");

        }
        System.out.println(sb);
    }
}
