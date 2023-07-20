import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[1000001];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= Math.abs(n); i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000000;
        }

        if (n == 0) {
            sb.append(0).append("\n");
        } else if (n < 0 && Math.abs(n) % 2 == 0) {
            sb.append(-1).append("\n");
        } else {
            sb.append(1).append("\n");
        }
        sb.append(dp[Math.abs(n)]);

        System.out.println(sb);
    }
}
