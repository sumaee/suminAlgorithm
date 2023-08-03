import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        //일반적인 시각 : Blue(0), Red(1), Green(2)
        int[][] noRedGreen = new int[n][n];
        //적록색약 시각 : Blue(0), Red & Green(1)
        int[][] redGreen = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                char color = str.charAt(j);
                if (color == 'R') {
                    noRedGreen[i][j] = 1;
                    redGreen[i][j] = 1;
                } else if (color == 'G') {
                    noRedGreen[i][j] = 2;
                    redGreen[i][j] = 1;
                } else if (color == 'B') {
                    noRedGreen[i][j] = 0;
                    redGreen[i][j] = 0;
                }
            }
        }

        visited = new boolean[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, noRedGreen);
                    cnt++;
                }
            }
        }

        sb.append(cnt).append(" ");
        visited = new boolean[n][n];
        cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, redGreen);
                    cnt++;
                }
            }
        }
        sb.append(cnt);

        System.out.println(sb);
    }

    private static void bfs(int row, int col, int[][] picture) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(row, col));
        visited[row][col] = true;

        int color = picture[row][col];

        while (!que.isEmpty()) {
            Node curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 현재 색과 같지 않거나 방문한 곳이라면 패스
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || picture[drow][dcol] != color || visited[drow][dcol])
                    continue;

                visited[drow][dcol] = true;
                que.offer(new Node(drow, dcol));
            }
        }
    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
