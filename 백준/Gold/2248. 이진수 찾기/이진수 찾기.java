import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        long i = Long.parseLong(st.nextToken());

        int[][] dp = new int[32][32];
        for (int j = 0; j <= 31; j++) {
            dp[j][0] = 1;
        }

        for (int j = 1; j <= 31; j++) {
            for (int k = 1; k <= j; k++) {
                if (j == k) dp[j][k] = 1;
                else dp[j][k] = dp[j - 1][k] + dp[j - 1][k - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int cnt = n; cnt > 0; cnt--) {
            long idx = 0;
            for (int j = 0; j <= l; j++) {
                idx += dp[cnt - 1][j];
            }

            if (idx < i) {
                sb.append("1");
                i -= idx;
                l--;
            } else {
                sb.append("0");
            }
        }

        System.out.println(sb);
    }
}
