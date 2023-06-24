import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
    }

    private static void bfs(int row, int col) {
        Queue<Node1> que = new LinkedList<>();

        que.offer(new Node1(row, col, 0, 1));
        visited[row][col][0] = true;
        visited[row][col][1] = true;

        while (!que.isEmpty()) {
            Node1 curr = que.poll();
            // 도착지에 도착했으면 답 뽑아내고 끝
            if (curr.row == n - 1 && curr.col == m - 1) {
                System.out.println(curr.dis);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나면 패스
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= m) continue;

                //벽인경우라면
                if (map[drow][dcol] == 1) {
                    //벽을 부술 수 있고 방문한 적이 없는 곳이라면
                    if (curr.breakCnt == 0 && !visited[drow][dcol][1]) {
                        visited[drow][dcol][1] = true;
                        que.offer(new Node1(drow, dcol, curr.breakCnt + 1, curr.dis + 1));
                    }
                } else {
                    if (!visited[drow][dcol][curr.breakCnt]) {
                        visited[drow][dcol][curr.breakCnt] = true;
                        que.offer(new Node1(drow, dcol, curr.breakCnt, curr.dis + 1));
                    }
                }
            }
        }

        //while문에서 안끝나면 답이 없는 것이므로 -1 출력
        System.out.println(-1);
    }
}

class Node1 {
    int row, col, breakCnt, dis;

    public Node1(int row, int col, int breakCnt, int dis) {
        this.row = row;
        this.col = col;
        this.breakCnt = breakCnt;
        this.dis = dis;
    }
}
