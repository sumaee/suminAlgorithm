import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][k + 1];

        dp[0][0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                //만약 j가 i 보다 커지는 경우 (k>n)
                if (j > i) {
                    dp[i][j] = 0;
                }

                //만약 j==i (k==n) 이거나 j==0 (k==0) 인경우
                else if (j == i || j == 0) {
                    dp[i][j] = 1;
                }

                //그외는 더하기
                else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
