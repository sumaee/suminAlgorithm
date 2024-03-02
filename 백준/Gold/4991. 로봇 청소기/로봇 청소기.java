import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int w, h;
    static char[][] room;
    static int[][] distArr;
    static List<RoomInfo> roomInfos;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[] check;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            room = new char[h][w];
            roomInfos = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    room[i][j] = str.charAt(j);

                    if (room[i][j] == 'o') {
                        roomInfos.add(0, new RoomInfo(i, j, 0));
                    } else if (room[i][j] == '*') {
                        roomInfos.add(new RoomInfo(i, j, 0));
                    }
                }
            }

            distArr = new int[roomInfos.size()][roomInfos.size()];
            int dirty = 0;
            // 로봇 청소기랑 먼지들 간의 거리 구하기
            for (int i = 0; i < roomInfos.size(); i++) {
                dirty += bfs(roomInfos.get(i), i);
            }


            //청소힐수 있는 먼지가 원래 개수랑 같다면 최소 거리 탐색
            if (dirty == roomInfos.size() - 1) {
                answer = Integer.MAX_VALUE;
                check = new boolean[roomInfos.size()];
                check[0] = true;
                perm(0, 1, 0);
                sb.append(answer).append("\n");
            } else {
                sb.append(-1).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void perm(int start, int cnt, int dist) {
        if (cnt == roomInfos.size()) {
            answer = Math.min(answer, dist);
        }

        for (int i = 1; i < roomInfos.size(); i++) {
            if (!check[i]) {
                check[i] = true;
                perm(i, cnt + 1, dist + distArr[start][i]);
                check[i] = false;
            }

        }
    }

    private static int bfs(RoomInfo info, int start) {
        boolean[][] visited = new boolean[h][w];
        Queue<RoomInfo> que = new LinkedList<>();

        que.offer(info);
        visited[info.row][info.col] = true;

        int dirty = 0;
        while (!que.isEmpty()) {
            RoomInfo curr = que.poll();

            //먼지의 위치라면 로봇 청소기와의 거리 확인
            if (room[curr.row][curr.col] == '*') {
                if (start == 0) {
                    dirty++;
                }

                for (int i = 1; i < roomInfos.size(); i++) {
                    if (curr.row == roomInfos.get(i).row && curr.col == roomInfos.get(i).col) {
                        distArr[start][i] = curr.moveCnt;
                    }

                }
            }

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || dcol < 0 || drow >= h || dcol >= w || visited[drow][dcol] || room[drow][dcol] == 'x')
                    continue;

                visited[drow][dcol] = true;
                que.offer(new RoomInfo(drow, dcol, curr.moveCnt + 1));
            }
        }

        return dirty;
    }

    static class RoomInfo {
        int row, col, moveCnt;

        public RoomInfo(int row, int col, int moveCnt) {
            this.row = row;
            this.col = col;
            this.moveCnt = moveCnt;
        }
    }

}