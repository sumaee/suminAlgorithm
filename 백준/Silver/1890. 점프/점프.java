import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        long[][] dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;
                //이동한 행이 범위를 벗어나지 않는다면
                if (board[i][j] + i < n) {
                    //dp에 현재 까지 올 수 있었던 방법을 더하기
                    dp[i + board[i][j]][j] += dp[i][j];
                }

                //오른쪽으로 이동하는 것도 마찬가지
                if (board[i][j] + j < n) {
                    dp[i][board[i][j] + j] += dp[i][j];
                }
            }
        }

        System.out.println(dp[n - 1][n - 1]);
    }
}
