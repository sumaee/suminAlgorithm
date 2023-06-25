import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m;
    static List<Integer> count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];
        count = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int startC = Integer.parseInt(st.nextToken());
            int startR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken()) - 1;
            int endR = Integer.parseInt(st.nextToken()) - 1;

            //사각형 부분 1로 채우기
            for (int row = startR; row <= endR; row++) {
                for (int col = startC; col <= endC; col++) {
                    map[row][col] = 1;
                }
            }
        }

        //구역 확인
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        Collections.sort(count);

        sb.append(cnt).append("\n");
        for (int mapSize : count) {
            sb.append(mapSize).append(" ");
        }

        System.out.println(sb);
    }

    private static void bfs(int row, int col) {
        Queue<Node1> que = new LinkedList<>();
        que.offer(new Node1(row, col));
        visited[row][col] = true;

        int cnt = 1;
        while (!que.isEmpty()) {
            Node1 curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위 벗어나거나 방문했던 곳이거나 사각형 부분이라면 패스
                if (drow < 0 || drow >= m || dcol < 0 || dcol >= n || visited[drow][dcol] || map[drow][dcol] == 1)
                    continue;

                //방문 처리 해주고 개수 증가 후 큐에 넣기
                visited[drow][dcol] = true;
                cnt++;
                que.offer(new Node1(drow, dcol));
            }
        }

        count.add(cnt);
    }
}

class Node1 {
    int row, col;

    public Node1(int row, int col) {

        this.row = row;
        this.col = col;
    }
}
