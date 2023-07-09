import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][][] dp = new long[n + 1][10][1 << 10];
        long mod = 1000000000;

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int b = 0; b < (1 << 10); b++) {
                    int curr = b | (1 << j);

                    //숫자가 0이라면 다음 숫자는 1밖에 못옴
                    if (j == 0) {
                        dp[i][j][curr] += dp[i - 1][j + 1][b] % mod;
                    }
                    //숫자가 9라면 다음 숫자는 8밖에 못옴
                    else if (j == 9) {
                        dp[i][j][curr] += dp[i - 1][j - 1][b] % mod;
                    }
                    //그외에는 j+1, j-1 값이 올수 있음
                    else {
                        dp[i][j][curr] += (dp[i - 1][j - 1][b] + dp[i - 1][j + 1][b]) % mod;
                    }
                }
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer += dp[n][i][(1 << 10) - 1] % mod;
        }
        System.out.println(answer % mod);
    }
}
