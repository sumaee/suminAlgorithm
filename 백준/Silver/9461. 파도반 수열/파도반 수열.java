import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //dp 미리 채우기
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 1) {
                sb.append(dp[1]).append("\n");
            } else if (n == 2) {
                sb.append(dp[2]).append("\n");
            } else {
                sb.append(dp[n]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
