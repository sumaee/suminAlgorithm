import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        answer = new int[n][m];
        int startRow = 0;
        int startCol = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                answer[i][j] = -1;
                if (map[i][j] == 2) {
                    startRow = i;
                    startCol = j;
                } else if (map[i][j] == 0) {
                    answer[i][j] = -2;
                }
            }
        }

        visited = new boolean[n][m];
        bfs(startRow, startCol);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (answer[i][j] == -1) {
                    sb.append(-1).append(" ");
                } else if (answer[i][j] == -2) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(answer[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int row, int col) {
        Queue<Locate> que = new LinkedList<>();
        visited[row][col] = true;
        que.offer(new Locate(row, col, 0));
        answer[row][col] = 0;
        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위가 벗어나거나 방문했던 곳이거나 벽이라면
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= m || visited[drow][dcol] || map[drow][dcol] == 0)
                    continue;

                visited[drow][dcol] = true;
                answer[drow][dcol] = curr.dist + 1;
                que.offer(new Locate(drow, dcol, curr.dist + 1));
            }
        }
    }

    static class Locate {
        int row, col, dist;

        public Locate(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
