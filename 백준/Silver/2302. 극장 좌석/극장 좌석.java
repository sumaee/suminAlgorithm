import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int answer = 1;

        //vip좌석을 제외한 부분 을 계산
        int seat = 0;
        for (int i = 0; i < m; i++) {
            int vip = Integer.parseInt(br.readLine());
            answer *= dp[vip - seat - 1];
            seat = vip;
        }

        //맨 마지막 자리들
        answer *= dp[n - seat];
        System.out.println(answer);
    }
}
