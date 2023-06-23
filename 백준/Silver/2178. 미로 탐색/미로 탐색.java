import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] maze;
    static boolean[][] visited;
    static int n;
    static int m;
    static int answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = str.charAt(j) - '0';
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[n][m];
        bfs(0, 0);

        System.out.println(answer);

    }

    private static void bfs(int row, int col) {
        visited[row][col] = true;
        Queue<Node> que = new LinkedList<>();

        que.offer(new Node(row, col, 1));
        while (!que.isEmpty()) {
            Node curr = que.poll();
            //해당 위치가 도착점이면 비교
            if (curr.row == n - 1 && curr.col == m - 1) {
                answer = Math.min(answer, curr.cnt);
            }
            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어났거나 방문했던 곳이거나 벽이라면 다음
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= m || visited[drow][dcol] || maze[drow][dcol] == 0)
                    continue;

                //큐에 넣고 방문처리
                visited[drow][dcol] = true;
                que.offer(new Node(drow, dcol, curr.cnt + 1));
            }

        }
    }
}

class Node {
    int row, col, cnt;

    public Node(int row, int col, int cnt) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
    }
}
