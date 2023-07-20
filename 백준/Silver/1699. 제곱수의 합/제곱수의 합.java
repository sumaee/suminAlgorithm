import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        //제곱수 1로 저장
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }

        int answer = find(n);
        System.out.println(answer);
    }

    private static int find(int num) {
        if (dp[num] == 0) {
            dp[num] = 100001;
            for (int i = (int) Math.sqrt(num); i >= 0; i--) {
                int temp = (int) Math.pow(i, 2);
                dp[num] = Math.min(find(num - temp) + 1, dp[num]);
            }
        }
        return dp[num];
    }
}
