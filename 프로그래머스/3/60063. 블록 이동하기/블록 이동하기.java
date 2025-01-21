import java.lang.*;
import java.util.*;
class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][][] visited;
    public int solution(int[][] board) {
        int len = board.length;
        visited = new boolean[len][len][2];
        int answer = 0;

        Queue<Drone> que = new LinkedList<>();
        que.offer(new Drone(new Locate(0, 0), new Locate(0, 1), 0, 0));

        while (!que.isEmpty()) {
            Drone drone = que.poll();
            int r1 = drone.l1.row;
            int c1 = drone.l1.col;
            int r2 = drone.l2.row;
            int c2 = drone.l2.col;




            // 해당 위치가 범위를 벗어나는 경우
            if (r1 < 0 || c1 < 0 || r1 >= len || c1 >= len || r2 < 0 || c2 < 0 || r2 >= len || c2 >= len) {
                continue;
            }

            // 벽인 경우
            if (board[r1][c1] == 1 || board[r2][c2] == 1) continue;

            // 방문한 경우
            if (visited[r1][c1][drone.isVertical] && visited[r2][c2][drone.isVertical]) continue;
            
            if ((r1 == len - 1 && c1 == len - 1) || (r2 == len - 1 && c2 == len - 1)) {
                answer = drone.time;
                break;
            }

            visited[r1][c1][drone.isVertical] = true;
            visited[r2][c2][drone.isVertical] = true;

            for (int i = 0; i < 4; i++) {
                int dr1 = r1 + dr[i];
                int dc1 = c1 + dc[i];
                int dr2 = r2 + dr[i];
                int dc2 = c2 + dc[i];
                que.offer(new Drone(new Locate(dr1, dc1), new Locate(dr2, dc2), drone.time + 1, drone.isVertical));
            }

            //회전
            //가로인 경우
            if (drone.isVertical == 0) {
                // 위쪽방향으로 회전시켰을 때
                if (r1 - 1 >= 0 && board[r1 - 1][c1] == 0 && board[r2 - 1][c2] == 0) {
                    que.offer(new Drone(new Locate(r1 - 1, c2), new Locate(r2, c2), drone.time + 1, 1));
                    que.offer(new Drone(new Locate(r1, c1), new Locate(r2 - 1, c1), drone.time + 1, 1));
                }
                // 아래방향으로 회전시켰을 때
                if (r1 + 1 < len && board[r1 + 1][c1] == 0 && board[r2 + 1][c2] == 0) {
                    que.offer(new Drone(new Locate(r1, c1), new Locate(r2 + 1, c1), drone.time + 1, 1));
                    que.offer(new Drone(new Locate(r1 + 1, c2), new Locate(r2, c2), drone.time + 1, 1));
                }
            }
            // 세로일 경우
            else {
                // 오른쪽으로 이동
                if (c1 + 1 < len && board[r1][c1 + 1] == 0 && board[r2][c2 + 1] == 0) {
                    que.offer(new Drone(new Locate(r1, c1), new Locate(r1, c2 + 1), drone.time + 1, 0));
                    que.offer(new Drone(new Locate(r2, c1 + 1), new Locate(r2, c2), drone.time + 1, 0));
                }
                //왼쪽으로 이동
                if (c1 - 1 >= 0 && board[r1][c1 - 1] == 0 && board[r2][c2 - 1] == 0) {
                    que.offer(new Drone(new Locate(r1, c1), new Locate(r1, c2 - 1), drone.time + 1, 0));
                    que.offer(new Drone(new Locate(r2, c1 - 1), new Locate(r2, c2), drone.time + 1, 0));
                }
            }
        }

        return answer;
    }
    
    
    static class Drone {
        Locate l1, l2;
        int time;
        int isVertical;

        public Drone(Locate l1, Locate l2, int time, int isVertical) {
            this.l1 = l1;
            this.l2 = l2;
            this.time = time;
            this.isVertical = isVertical;
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