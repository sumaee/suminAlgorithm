import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, cnt, size;
    static int[][] painting;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        painting = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                painting[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
        cnt = 0;
        size = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (painting[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        System.out.println(cnt + "\n" + size);
    }

    private static void bfs(int row, int col) {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(row, col));

        visited[row][col] = true;

        int tempSize = 1;
        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || dcol < 0 || drow >= n || dcol >= m || visited[drow][dcol] || painting[drow][dcol] == 0)
                    continue;

                visited[drow][dcol] = true;
                tempSize++;
                que.offer(new Locate(drow, dcol));
            }
        }

        size = Math.max(size, tempSize);
    }

    static class Locate {
        int row, col;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}