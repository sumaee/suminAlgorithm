import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][][] dp;
    static int[] tree;
    static int t, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        dp = new int[t + 1][w + 1][3]; // t초에 w번 움직여서 해당 나무 위치에서 먹을 수 있는 자두의 최대값
        tree = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= t; i++) {
            for (int j = 0; j <= w; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        // 초반부터 움직여서 2번나무에서부터 시작한것과 처음에는 1번에서 시작한것중 제일 최댓값
        int answer = Math.max(dfs(1, 1, 2), dfs(1, 0, 1));
        System.out.println(answer);
    }

    private static int dfs(int time, int move, int treeNum) {
        //움직인횟수가 넘어가거나 시간이 초과되면 0
        if (time > t || move > w) return 0;
        if (dp[time][move][treeNum] != -1) return dp[time][move][treeNum];

        //현재 위치랑 떨어지는 자두 위치랑 같다면 1
        dp[time][move][treeNum] = treeNum == tree[time] ? 1 : 0;

        //현재에서 그냥 먹기와 이동해서 먹는 것중 최댓값 찾아서 현재 값이랑 더해주기
        dp[time][move][treeNum] += Math.max(dfs(time + 1, move, treeNum), dfs(time + 1, move + 1, 3 - treeNum));
        return dp[time][move][treeNum];
    }
}
