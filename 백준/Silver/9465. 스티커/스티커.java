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

            int[][] sticker = new int[2][n + 1];
            int[][] dp = new int[2][n + 1];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];

            if (n == 1) {
                sb.append(Math.max(sticker[0][1], sticker[1][1])).append("\n");
                continue;
            }

            //양 옆의 스티커를 못떼기 때문에 반대 줄의 한칸 전의 스티커를 사용했을 때와 두칸 전의 스티커를 사용했을 때 중 가장 큰 값을 누적시킴
            for (int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + sticker[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + sticker[1][i];
            }
            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }

        System.out.println(sb);
    }
}
