import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n + 1][n + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //(i-1)*dp[i-1][j] : 열쇠로 상자를 여는 경우
                //dp[i-1][j-1] : 폭탄을 사용해 상자를 여는 경우
                dp[i][j] = (i - 1) * dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        //모든 열쇠를 얻은 확률 = (주어진 폭탄을 이용해 상자를 모두 여는 경우의 수)/(폭탄과 상관없이 상자를 모두 여는 경우의 수)
        long total = 0;
        long bombCase = 0;
        for (int i = 1; i <= n; i++) {
            total += dp[n][i];

            if (i <= m) bombCase += dp[n][i];
        }

        long gcd = getGcd(total, bombCase);
        System.out.println(bombCase / gcd + "/" + total / gcd);
    }

    private static long getGcd(long a, long b) {
        return b != 0 ? getGcd(b, a % b) : a;
    }
}
