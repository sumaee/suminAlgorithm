import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 빌딩의 개수
        int l = Integer.parseInt(st.nextToken()); // 왼쪽에서 보는 빌딩의 수
        int r = Integer.parseInt(st.nextToken()); // 오른쪽에서 보는 빌딩의 수

        long[][][] dp = new long[n + 1][n + 1][n + 1];


        for (int i = 1; i <= n; i++) {
            //건물이 몇개든 한쪽에서 1개만 보인다는 것은 1가지밖에 없음
            dp[i][i][1] = 1;
            dp[i][1][i] = 1;
        }

        //건물의 개수만큼 진행
        for (int cnt = 2; cnt <= n; cnt++) {
            for (int left = 1; left <= l; left++) {
                for (int right = 1; right <= r; right++) {
                    dp[cnt][left][right] = (dp[cnt - 1][left][right] * (cnt - 2) + dp[cnt - 1][left - 1][right] + dp[cnt - 1][left][right - 1]) % 1000000007;
                }

            }
        }

        System.out.println(dp[n][l][r]);
    }
}
