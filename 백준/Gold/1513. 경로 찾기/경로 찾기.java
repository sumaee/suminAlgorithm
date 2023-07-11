import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final long MOD = 1000007;
    static int n, m;
    static long[][][][] dp; //행, 열, 방문 횟수, 현재 방문 번호
    static int[][] pc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        dp = new long[n + 1][m + 1][51][51];
        pc = new int[n + 1][m + 1];
        for (int i = 1; i <= c; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            pc[row][col] = i;
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= 50; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        for (int i = 0; i <= c; i++) {
            sb.append(dfs(1, 1, i, 0)).append(" ");
        }
        System.out.println(sb);
    }

    private static long dfs(int row, int col, int cnt, int prev) {
        //만약 범위를 벗어난다면 return 0;
        if (row > n || col > m) return 0;
        //만약 학원에 도착했다면
        if (row == n && col == m) {
            //만약 피시방에 가는 횟수가 0이고 도착한 지점이 피시방이 아니라면 1 return
            if (cnt == 0 && pc[row][col] == 0) return 1;
            //만약 피시방에 갈수 있는 횟수가 1번 남았고, 도착지가 피시방이라면
            if (cnt == 1 && pc[row][col] > prev) return 1;
            //그외의 경우들은 모두 0
            return 0;
        }
        if (cnt < 0) return 0;

        if (dp[row][col][cnt][prev] != -1) return dp[row][col][cnt][prev];

        dp[row][col][cnt][prev] = 0;
        //만약 현재 위치가 아무것도 아닌 곳이라면 다음으로 이동
        //다음으로 이동할 수 있는 부분은 밑으로 내려가거나 한칸 오른쪽으로 이동하는 것
        if (pc[row][col] == 0) {
            dp[row][col][cnt][prev] = (dfs(row + 1, col, cnt, prev) + dfs(row, col + 1, cnt, prev)) % MOD;
        }

        //만약 현재 위치가 피시방이라면 횟수 한번 줄이고 이동
        else if (pc[row][col] > prev) {
            dp[row][col][cnt][prev] = (dfs(row + 1, col, cnt - 1, pc[row][col]) + dfs(row, col + 1, cnt - 1, pc[row][col])) % MOD;
        }

        return dp[row][col][cnt][prev];
    }
}
