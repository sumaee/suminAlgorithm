import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] dp = new long[n + 1];

        Arrays.fill(dp, 1);

        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j] += dp[j - 1] % 1000000000;
            }
        }

        System.out.println(dp[n]%1000000000);
    }
}
