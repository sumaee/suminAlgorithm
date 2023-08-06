import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        Locate start = null;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'I') {
                    start = new Locate(i, j);
                }
            }
        }

        bfs(start, n, m);
        System.out.println(answer == 0 ? "TT" : answer);
    }

    private static void bfs(Locate start, int n, int m) {
        Queue<Locate> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        que.offer(start);
        visited[start.row][start.col] = true;

        while (!que.isEmpty()) {
            Locate curr = que.poll();
            //사람을 만났다면 answer++
            if (map[curr.row][curr.col] == 'P') answer++;

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위가 벗어나거나 방문했었거나 벽이라면 패스
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= m || visited[drow][dcol] || map[drow][dcol] == 'X')
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
