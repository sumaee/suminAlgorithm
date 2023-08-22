import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static int[][] map;
    static boolean[][] visited, cycleCheck;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char dir = line.charAt(j);

                if (dir == 'U') map[i][j] = 0;
                else if (dir == 'R') map[i][j] = 1;
                else if (dir == 'D') map[i][j] = 2;
                else if (dir == 'L') map[i][j] = 3;
            }
        }

        visited = new boolean[n][m];
        cycleCheck = new boolean[n][m];
        answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //방문하지 않은 곳이라면 사이클 여부 확인 진행
                if (!visited[i][j]) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int row, int col) {
        visited[row][col] = true;

        int drow = row + dr[map[row][col]];
        int dcol = col + dc[map[row][col]];

        //방문하지 않았던 곳이라면 다음으로 넘어가기
        if (!visited[drow][dcol]) {
            dfs(drow, dcol);
        }
        //방문했던 곳이라면
        else {
            //사이클 체크가 안되있는 곳이라면 해당 지역에 safezone 설치
            if (!cycleCheck[drow][dcol]) answer++;
        }

        //사이클 도는 위치 체크
        cycleCheck[row][col] = true;
    }
}
