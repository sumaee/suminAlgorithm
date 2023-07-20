import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Counsel[] counsels = new Counsel[n + 1];
        int[] dp = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            counsels[i] = new Counsel(time, cost);
        }

        for (int i = n; i > 0; i--) {
            if (i + counsels[i].time > n + 1) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], dp[i + counsels[i].time] + counsels[i].cost);
            }
        }
        System.out.println(dp[1]);
    }

    static class Counsel {
        int time, cost;

        public Counsel(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }
    }
}
