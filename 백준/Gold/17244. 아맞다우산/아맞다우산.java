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
    static Locate start;
    static int n, m, itemCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[m][n];
        char idx = '0';
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'S') {
                    start = new Locate(i, j, 0, 0);
                } else if (map[i][j] == 'X') {
                    map[i][j] = idx++;
                    itemCnt++;
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Locate> que = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][1 << itemCnt];

        que.offer(start);
        visited[start.row][start.col][0] = true;
        while (!que.isEmpty()) {
            Locate curr = que.poll();
            if (map[curr.row][curr.col] == 'E' && curr.item == ((1 << itemCnt) - 1)) {
                return curr.move;
            }

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || drow >= m || dcol < 0 || dcol >= n
                        || map[drow][dcol] == '#' || visited[drow][dcol][curr.item]) {
                    continue;
                }

                visited[drow][dcol][curr.item] = true;
                if (map[drow][dcol] >= '0' && map[drow][dcol] <= '5') {
                    int idx = map[drow][dcol] - '0';

                    que.offer(new Locate(drow, dcol, curr.item | (1 << idx), curr.move + 1));
                } else {
                    que.offer(new Locate(drow, dcol, curr.item, curr.move + 1));
                }
            }
        }

        return 0;
    }

    static class Locate {
        int row, col, item, move;

        public Locate(int row, int col, int item, int move) {
            this.row = row;
            this.col = col;
            this.item = item;
            this.move = move;
        }
    }
}