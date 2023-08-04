import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int row, int col, int depth, int sum) {
        //깊이가 4가 되면 4개 다 연결한 것이므로 종료
        if (depth >= 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //범위가 벗어나거나 이미 방문했다면 패스
            if (drow < 0 || dcol < 0 || drow >= n || dcol >= m || visited[drow][dcol]) continue;

            //깊이가 2였을 때 ㅗ 모양을 만들 수있으므로 한번 더 확인
            if (depth == 2) {
                visited[drow][dcol] = true;
                dfs(row, col, depth + 1, sum + arr[drow][dcol]);
                visited[drow][dcol] = false;
            }

            visited[drow][dcol] = true;
            dfs(drow, dcol, depth + 1, sum + arr[drow][dcol]);
            visited[drow][dcol] = false;
        }
    }
}
