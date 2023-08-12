import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, answer;
    static int[][] origin;
    static List<Node> virus;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        origin = new int[n][m];
        virus = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
                if (origin[i][j] == 2) {
                    virus.add(new Node(i, j));
                }
            }
        }

        answer = Integer.MIN_VALUE;
        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int cnt) {
        //벽의 개수가 3개가 되면 안전지대 확인
        if (cnt == 3) {
            //바이러스 퍼뜨리기
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (origin[i][j] == 0) {
                    origin[i][j] = 1;
                    dfs(cnt + 1);
                    origin[i][j] = 0;
                }
            }
        }
    }

    private static void bfs() {
        //배열을 복사해서 바이러스 퍼뜨리기
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            copy[i] = origin[i].clone();
        }

        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        //바이러스 위치 넣기
        for (int i = 0; i < virus.size(); i++) {
            int row = virus.get(i).row;
            int col = virus.get(i).col;
            que.offer(new Node(row, col));
            visited[row][col] = true;
        }

        //바이러스 퍼뜨리기
        while (!que.isEmpty()) {
            Node curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위가 벗어나거나 방문했거나 벽이라면 패스
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= m || visited[drow][dcol] || copy[drow][dcol] == 1)
                    continue;

                visited[drow][dcol] = true;
                copy[drow][dcol] = 2;
                que.offer(new Node(drow, dcol));
            }
        }

        //안전지대 세기
        int safeZone = countSafe(copy);
        //개수 갱신
        answer = Math.max(safeZone, answer);
    }

    private static int countSafe(int[][] copy) {
        int cnt = 0;
        for (int[] a : copy) {
            for (int b : a) {
                if (b == 0) cnt++;
            }
        }
        return cnt;
    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
