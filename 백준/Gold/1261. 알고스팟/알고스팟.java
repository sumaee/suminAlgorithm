import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] arr, cnt;
    static boolean[][] visited;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m][n];
        cnt = new int[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < m; i++) {
            Arrays.fill(cnt[i], 987654321);
        }
        bfs(0, 0);
        System.out.println(cnt[m - 1][n - 1]);
    }

    private static void bfs(int row, int col) {
        PriorityQueue<Node4> que = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        que.offer(new Node4(row, col, 0));
        visited[row][col] = true;
        cnt[row][col] = 0;

        while (!que.isEmpty()) {
            Node4 curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || drow >= m || dcol < 0 || dcol >= n || visited[drow][dcol]) continue;

                visited[drow][dcol] = true;
                //만약 벽이라면 현재까지 부순 벽에서 +1 하는것과 다른 길로 와서 부순것 중에서 제일 작은 값을 넣어줌
                if (arr[drow][dcol] == 1) {
                    cnt[drow][dcol] = Math.min(cnt[drow][dcol], curr.cnt + 1);
                }
                //벽이 아니라면 현재까지 부순 벽의 횟수와 현재 가진 횟수중 제일 작은 값을 넣어줌
                else {
                    cnt[drow][dcol] = Math.min(cnt[drow][dcol], curr.cnt);
                }

                que.offer(new Node4(drow, dcol, cnt[drow][dcol]));
            }

        }
    }
}

class Node4 {
    int row, col, cnt;

    public Node4(int row, int col, int cnt) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
    }
}
