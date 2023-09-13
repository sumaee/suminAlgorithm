import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] isTop;
    static int[][] map;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        isTop = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //산봉우리가 아니고 0이 아니라면 bfs 실행
                if (!isTop[i][j] && map[i][j] != 0) {
                    boolean result = bfs(i, j);
                    if (result) answer++;
                }

            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int row, int col) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(row, col));

        boolean[][] visited = new boolean[n][m];
        visited[row][col] = true;

        List<Node> tops = new ArrayList<>();
        while (!que.isEmpty()) {
            Node curr = que.poll();

            //8방 탐색
            for (int i = 0; i < 8; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나지 않는지 확인
                if (drow >= n || dcol >= m || drow < 0 || dcol < 0 || visited[drow][dcol]) continue;

                //만약 다음 높이가 현재 높이보다 높다면 현재는 산봉우리가 아니므로 return false
                if (map[drow][dcol] > map[curr.row][curr.col]) {
                    return false;
                }
                //만약 현재와 같은 높이라면 같은 산맥의 산봉우리이므로 추가
                else if (map[drow][dcol] == map[curr.row][curr.col]) {
                    que.offer(new Node(drow, dcol));
                    tops.add(new Node(drow, dcol));
                }

                visited[drow][dcol] = true;
            }
        }

        //같은 높이의 산봉우리 방문처리 해주기
        for (Node top : tops) {
            isTop[top.row][top.col] = true;
        }

        return true;
    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
