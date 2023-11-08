import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //k, n이 14이므로 미리 채워넣기
        int[][] dp = new int[15][15];
        //0층의 i호에는 i명이 살고 있음
        for (int i = 1; i < 15; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            sb.append(dp[k][n]).append("\n");
        }

        System.out.println(sb);
    }
}