import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Locate start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') start = new Locate(i, j, 0, 0);
            }
        }
        
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Locate> que = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][64];
        que.offer(start);
        visited[start.row][start.col][start.key] = true;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            if(map[curr.row][curr.col] == '1') return curr.cnt;

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if(drow<0 || dcol<0 || drow>=n || dcol >=m) continue;
                if(map[drow][dcol] == '#' || visited[drow][dcol][curr.key]) continue;

                if (map[drow][dcol] >= 'a' && map[drow][dcol] <= 'f') {
                    int key = (1 << map[drow][dcol] - 'a') | curr.key;
                    visited[drow][dcol][key] = true;
                    que.offer(new Locate(drow, dcol, curr.cnt + 1, key));
                } else if (map[drow][dcol] >= 'A' && map[drow][dcol] <= 'F') {
                    int key = curr.key & (1 << map[drow][dcol] - 'A');
                    if (key == (int) Math.pow(2, map[drow][dcol] - 'A')) {
                        visited[drow][dcol][curr.key] = true;
                        que.offer(new Locate(drow, dcol, curr.cnt + 1, curr.key));
                    }
                } else {
                    visited[drow][dcol][curr.key] = true;
                    que.offer(new Locate(drow, dcol, curr.cnt + 1, curr.key));
                }
            }
        }

        return -1;
    }

    static class Locate{
        int row, col, cnt, key;

        public Locate(int row, int col, int cnt, int key) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.key = key;
        }
    }
}