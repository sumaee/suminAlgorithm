import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //모든 위치를 돌면서 정답 확인
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
    }

    private static int dfs(int row, int col) {
        //만약 해당 위치에 값이 있다면 그냥 해당 값을 리턴
        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        //해당 위치를 방문했으므로 1로 초기화
        dp[row][col] = 1;

        //상하좌우를 돌면서 현재 위치보다 큰 곳으로 이동
        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //범위를 벗어나거나 현재 위치보다 양이 적다면 패스
            if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || map[drow][dcol] <= map[row][col]) continue;

            //해당 위치의 다음으로 이동해서 최댓값 갱신
            dp[row][col] = Math.max(dp[row][col], dfs(drow, dcol) + 1);
        }

        return dp[row][col];
    }
}