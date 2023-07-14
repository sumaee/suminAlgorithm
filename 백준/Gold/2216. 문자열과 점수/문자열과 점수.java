import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static String str1, str2;
    static int a, b, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        str1 = br.readLine();
        str2 = br.readLine();
        dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            dp[i][0] = dp[i - 1][0] + b;
        }
        for (int i = 1; i <= str2.length(); i++) {
            dp[0][i] = dp[0][i - 1] + b;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                int score;
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    score = dp[i - 1][j - 1] + a;
                } else {
                    score = dp[i - 1][j - 1] + c;
                }

                dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]) + b, score);
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);
    }
}
