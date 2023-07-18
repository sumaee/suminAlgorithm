import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int num, n, answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Locate> edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        edge = new ArrayList<>();

        //일단 정보 받기 섬 구분을 위해 섬인 부분을 -1로 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int locate = Integer.parseInt(st.nextToken());
                if (locate == 1) {
                    map[i][j] = -1;
                }
            }
        }

        //섬 구분 짓기
        num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) {
                    divideIsland(i, j);
                    num++;
                }
            }
        }

        answer = Integer.MAX_VALUE;
        //외곽에서 다른 외곽으로의 최단 거리 찾기
        for (int i = 0; i < edge.size(); i++) {
            bfs(edge.get(i).row, edge.get(i).col, edge.get(i).num);
        }

        System.out.println(answer);

    }

    private static void bfs(int row, int col, int islandNum) {
        //현재의 외곽에서 다른 외곽까지의 거리들을 구함
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(row, col));
        dist[row][col] = 0;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 바다가 아니거나 해당 위치의 거리가 -1이 아니라면 패스
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || map[drow][dcol] != 0 || dist[drow][dcol] != -1)
                    continue;

                dist[drow][dcol] = dist[curr.row][curr.col] + 1;
                que.offer(new Locate(drow, dcol));
            }
        }

        //하나의 외곽에서 다른 외곽까지의 거리를 구했으므로 다른 섬까지의 외곽중 거리가 제일 짧은 값으로 정답 갱신
        for (int i = 0; i < edge.size(); i++) {
            //현재 외곽과 같은 섬이라면 패스
            if (edge.get(i).num == islandNum) continue;

            //다른 외곽이라면 해당 위치의 상하좌우의 바다 중 제일 짧은 값 고르기
            for (int j = 0; j < 4; j++) {
                int drow = edge.get(i).row + dr[j];
                int dcol = edge.get(i).col + dc[j];

                //범위를 벗어나거나 거리가 -1이라면 패스
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || dist[drow][dcol] == -1) continue;

                answer = Math.min(answer, dist[drow][dcol]);
            }
        }
    }

    private static void divideIsland(int row, int col) {
        Queue<Locate> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        que.offer(new Locate(row, col));
        visited[row][col] = true;
        map[row][col] = num;

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            //해당 위치가 외곽인지 확인
            if (isEdge(curr.row, curr.col)) {
                edge.add(new Locate(curr.row, curr.col, num));
            }
            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 이미 방문한 곳이거나 바다라면 패스
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || visited[drow][dcol] || map[drow][dcol] == 0)
                    continue;


                visited[drow][dcol] = true;
                map[drow][dcol] = num;
                que.offer(new Locate(drow, dcol));
            }
        }
    }

    private static boolean isEdge(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            if (drow < 0 || dcol < 0 || drow >= n || dcol >= n) continue;

            //해당 위치가 바다라면 외곽이므로 return true;
            if (map[drow][dcol] == 0) return true;
        }

        return false;
    }

    static class Locate {
        int row, col, num;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Locate(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }
    }
}
