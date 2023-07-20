import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[1001][10];

        Arrays.fill(dp[1], 1);

        //자릿수
        for (int i = 2; i <= n; i++) {
            for (int j = 9; j >= 0; j--) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i - 1][k] % 10007;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer += dp[n][i] % 10007;
        }

        System.out.println(answer%10007);
    }
}
