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
        int m = Integer.parseInt(st.nextToken());

        int[] boys = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            boys[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(boys);

        int[] girls = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            girls[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(girls);
        int row = Math.min(n, m);
        int col = Math.max(n, m);
        int[][] dp = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = i; j <= col; j++) {
                int temp;
                if (n > m) {
                    temp = Math.abs(boys[j] - girls[i]);
                } else {
                    temp = Math.abs(boys[i] - girls[j]);
                }

                if (i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + temp;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + temp, dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[row][col]);

    }
}