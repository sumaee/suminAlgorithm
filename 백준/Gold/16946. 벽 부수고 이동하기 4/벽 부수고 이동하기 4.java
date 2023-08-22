import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map, group;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static HashMap<Integer, Integer> zeroGroup;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        group = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        //0인부분 그룹화 하기
        zeroGroup = new HashMap<>();
        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && group[i][j] == 0) {
                    zeroGroup.put(idx, bfs(i, j, idx));
                    idx++;
                }
            }
        }

        //벽인 부분과 0인 부분 합치기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    sb.append(countMap(i, j));
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int countMap(int row, int col) {
        int cnt = 1;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //범위를 벗어나거나 0이라면 패스
            if (drow < 0 || drow >= n || dcol < 0 || dcol >= m || group[drow][dcol] == 0) continue;

            set.add(group[drow][dcol]);
        }

        for (int count : set) {
            cnt += zeroGroup.get(count);
        }

        return cnt % 10;
    }

    private static int bfs(int row, int col, int idx) {
        int answer = 1;
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(row, col));
        group[row][col] = idx;
        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 방문한 곳이거나 벽이라면 패스
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= m || group[drow][dcol] != 0 || map[drow][dcol] == 1)
                    continue;

                group[drow][dcol] = idx;
                que.offer(new Locate(drow, dcol));
                answer++;
            }
        }

        return answer;
    }

    static class Locate {
        int row, col;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
