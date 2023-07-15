import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Set<Integer> smallStone = new HashSet<>();
        for (int i = 0; i < m; i++) {
            smallStone.add(Integer.parseInt(br.readLine()));
        }

        int[][] dp = new int[n + 1][(int) Math.sqrt(n * 2) + 2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 987654321);
        }
        dp[1][0] = 0;

        int ans = 987654321;
        for (int idx = 2; idx <= n; idx++) {
            if (smallStone.contains(idx)) continue;

            for (int speed = 1; speed <= (int) Math.sqrt(idx * 2); speed++) {
                int minValue = Math.min(Math.min(dp[idx - speed][speed - 1], dp[idx - speed][speed]), dp[idx - speed][speed + 1]);
                if (minValue == 987654321) continue;
                dp[idx][speed] = minValue + 1;
                if (idx == n) ans = Math.min(ans, dp[idx][speed]);
            }
        }

        System.out.println(ans == 987654321 ? -1 : ans);

    }
}
