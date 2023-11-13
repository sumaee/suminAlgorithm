import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        dp = new long[30][30];

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(findAnswer(n, m)).append("\n");
        }

        System.out.println(sb);

    }

    private static Long findAnswer(int r, int n) {

        //이미 있다면 그냥 리턴
        if (dp[n][r] > 0) return dp[n][r];

        //조합 조건
        if (n == r || r == 0) return dp[n][r] = 1L;

        return dp[n][r] = findAnswer(r - 1, n - 1) + findAnswer(r, n - 1);
    }
}