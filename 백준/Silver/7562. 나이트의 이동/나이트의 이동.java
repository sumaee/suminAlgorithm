import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int i, startR, startC, endR, endC;
    static Locate[] locates;
    static boolean[][] visited;
    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            i = Integer.parseInt(br.readLine());
            locates = new Locate[2];

            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());

                locates[j] = new Locate(row, col);
            }

            visited = new boolean[i][i];
            sb.append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs() {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(locates[0].row, locates[0].col, 0));

        visited[locates[0].row][locates[0].col] = true;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            if (curr.row == locates[1].row && curr.col == locates[1].col) return curr.cnt;

            for (int j = 0; j < 8; j++) {
                int drow = curr.row + dr[j];
                int dcol = curr.col + dc[j];

                if (drow < 0 || dcol < 0 || drow >= i || dcol >= i || visited[drow][dcol]) continue;

                visited[drow][dcol] = true;
                que.offer(new Locate(drow, dcol, curr.cnt + 1));
            }
        }

        return 0;
    }

    static class Locate {
        int row, col, cnt;

        public Locate(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}