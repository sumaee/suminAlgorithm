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

        App[] apps = new App[n];
        StringTokenizer memoryInfo = new StringTokenizer(br.readLine());
        StringTokenizer costInfo = new StringTokenizer(br.readLine());
        int maxCost = 0;
        for (int i = 0; i < n; i++) {
            int memory = Integer.parseInt(memoryInfo.nextToken());
            int cost = Integer.parseInt(costInfo.nextToken());
            apps[i] = new App(memory, cost);
            maxCost += cost;
        }

        int[][] dp = new int[n][maxCost + 1];

        int answer = Integer.MAX_VALUE;
        for (int use = 0; use < n; use++) {
            int cost = apps[use].cost;
            int memory = apps[use].memory;
            for (int money = 0; money <= maxCost; money++) {

                //앱이 하나일 경우 해당 앱의 비용보다 큰 것들은 해당 앱의 메모리만큼만 사용가능
                if (use == 0) {
                    if (money >= cost) dp[use][money] = memory;
                } else {
                    if (money >= cost) {
                        dp[use][money] = Math.max(dp[use - 1][money - cost] + memory, dp[use - 1][money]);
                    } else {
                        dp[use][money] = dp[use - 1][money];
                    }
                }

                //주어진 메모리 이상 확보했다면 해당 비용이 정답이므로 작은 값으로 갱신
                if (dp[use][money] >= m) answer = Math.min(answer, money);
            }
        }

        System.out.println(answer);
    }

    static class App {
        int memory, cost;

        public App(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }
    }
}
