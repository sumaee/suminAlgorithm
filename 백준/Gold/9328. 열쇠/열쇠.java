import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static boolean[] key;
    static List<Locate>[] doors;
    static int r, c, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map = new char[r + 2][c + 2];
            for (int i = 0; i <= r + 1; i++) {
                Arrays.fill(map[i], '.');
            }

            for (int i = 1; i <= r; i++) {
                String str = br.readLine();
                for (int j = 1; j <= c; j++) {
                    map[i][j] = str.charAt(j - 1);
                }
            }

            String keys = br.readLine();
            key = new boolean[26];
            if (!keys.equals("0")) {
                for (int i = 0; i < keys.length(); i++) {
                    key[keys.charAt(i) - 'a'] = true;
                }
            }

            doors = new List[26];
            visited = new boolean[r + 2][c + 2];
            for (int i = 0; i < 26; i++) {
                doors[i] = new ArrayList<>();
            }
            answer = 0;
            bfs();
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(0, 0));
        visited[0][0] = true;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 방문했거나 벽이라면 패스
                if (drow < 0 || dcol < 0 || drow >= r + 2 || dcol >= c + 2 || visited[drow][dcol] || map[drow][dcol] == '*')
                    continue;

                //문이 있는 곳이라면
                if (map[drow][dcol] - 'A' >= 0 && map[drow][dcol] - 'A' <= 25) {
                    //열수 있는 열쇠가 있다면
                    if (key[map[drow][dcol] - 'A']) {
                        visited[drow][dcol] = true;
                        map[drow][dcol] = '.';
                        que.offer(new Locate(drow, dcol));
                    }
                    //열쇠가 없다면 대기열에 넣기
                    else {
                        doors[map[drow][dcol] - 'A'].add(new Locate(drow, dcol));
                    }
                }

                //열쇠가 있는 곳이라면
                else if (map[drow][dcol] - 'a' >= 0 && map[drow][dcol] - 'a' <= 25) {
                    //열쇠 추가
                    char keyAlpha = map[drow][dcol];
                    key[map[drow][dcol] - 'a'] = true;
                    map[drow][dcol] = '.';
                    visited[drow][dcol] = true;
                    que.offer(new Locate(drow, dcol));

                    //대기 열에 있는 문을 열수 있는지 확인하고 열수있다면 큐에 추가
                    for (Locate door : doors[keyAlpha - 'a']) {
                        visited[door.row][door.col] = true;
                        que.offer(new Locate(door.row, door.col));
                    }
                }

                //문서가 있는 곳이라면 문서 추가
                else if (map[drow][dcol] == '$') {
                    map[drow][dcol] = '.';
                    visited[drow][dcol] = true;
                    answer++;
                    que.offer(new Locate(drow, dcol));
                }
                //그냥 빈칸이라면
                else if (map[drow][dcol] == '.') {
                    que.offer(new Locate(drow, dcol));
                    visited[drow][dcol] = true;
                }
            }

        }
    }

    static class Locate {
        int row, col;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
