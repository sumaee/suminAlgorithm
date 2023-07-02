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
            int k = Integer.parseInt(br.readLine());

            int[] file = new int[k + 1];
            int[] sum = new int[k + 1];
            int[][] dp = new int[k + 1][k + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                file[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + file[i];
            }

            for (int i = 1; i <= k; i++) {
                for (int start = 1; start + i <= k; start++) {
                    int end = i + start;
                    dp[start][end] = Integer.MAX_VALUE;
                    for (int j = start; j < end; j++) {
                        dp[start][end] = Math.min(dp[start][end], dp[start][j] + dp[j + 1][end] + sum[end] - sum[start - 1]);
                    }

                }
            }

            sb.append(dp[1][k]).append("\n");
        }
        System.out.println(sb);
    }
}
