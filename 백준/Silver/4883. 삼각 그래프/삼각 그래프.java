import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            sb.append(tc++).append(". ");

            int[][] arr = new int[n][3];
            int[][] dp = new int[n][3];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = arr[0][0];
            dp[0][1] = arr[0][1];
            dp[0][2] = dp[0][1] + arr[0][2];

            //(0, 0)에서 다음행으로 갈 수 없으므로 첫번째 행의 Dp는 다르게 채워주기
            dp[1][0] = dp[0][1] + arr[1][0];
            dp[1][1] = Math.min(Math.min(dp[1][0], dp[0][1]), dp[0][2]) + arr[1][1];
            dp[1][2] = Math.min(Math.min(dp[0][1], dp[0][2]), dp[1][1]) + arr[1][2];

            //3번째 행부터는 첫번째 컬럼에는 위에서, 오른쪽 대각선에서 올수 있음
            //두번째 컬럼은 왼쪽, 왼쪽 대각선 위, 위, 오른쪽 대각선 위에서 올수 있음
            //세번째 컬럼은 왼쪽, 왼쪽 대각선위, 위에서 올수 있음
            int row = 2;
            while (row < n) {
                dp[row][0] = Math.min(dp[row - 1][0], dp[row - 1][1]) + arr[row][0];
                dp[row][1] = Math.min(Math.min(dp[row][0], dp[row - 1][0]), Math.min(dp[row - 1][1], dp[row - 1][2])) + arr[row][1];
                dp[row][2] = Math.min(Math.min(dp[row][1], dp[row - 1][1]), dp[row - 1][2]) + arr[row][2];
                row++;
            }

            sb.append(dp[n - 1][1]).append("\n");
        }

        System.out.println(sb);
    }
}
