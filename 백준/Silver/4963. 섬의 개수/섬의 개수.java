import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int h, w;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            //종료 조건
            if (w == 0 && h == 0) {
                break;
            }
            map = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);

    }

    private static void bfs(int row, int col) {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(row, col));

        visited[row][col] = true;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 8; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || dcol < 0 || drow >= h || dcol >= w || visited[drow][dcol] || map[drow][dcol] == 0)
                    continue;

                visited[drow][dcol] = true;
                que.offer(new Locate(drow, dcol));
            }
        }
    }

    static class Locate {
        int row, col;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}