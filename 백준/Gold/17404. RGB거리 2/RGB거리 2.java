import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int[][] costs, dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        costs = new int[n][3];
        //비용 일단 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        //만약 첫번째 집을 빨간색으로 선택했다면
        dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = INF;
        dp[0][2] = INF;
        checkMin();
        //마지막 집은 빨간색을 빼고 다른 색으로 골라아하므로
        answer = Math.min(answer, Math.min(dp[n - 1][1], dp[n - 1][2]));

        //만약 첫번째 집을 초록색으로 선택했다면
        dp = new int[n][3];
        dp[0][1] = costs[0][1];
        dp[0][0] = INF;
        dp[0][2] = INF;
        checkMin();
        //마지막 집은 초록색을 빼고 다른 색으로 골라아하므로
        answer = Math.min(answer, Math.min(dp[n - 1][0], dp[n - 1][2]));

        //만약 첫번째 집을 파란색으로 선택했다면
        dp = new int[n][3];
        dp[0][2] = costs[0][2];
        dp[0][0] = INF;
        dp[0][1] = INF;
        checkMin();
        //마지막 집은 파란색을 빼고 다른 색으로 골라아하므로
        answer = Math.min(answer, Math.min(dp[n - 1][0], dp[n - 1][1]));

        System.out.println(answer);
    }

    private static void checkMin() {
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
    }
}
