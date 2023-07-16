import java.lang.*;
import java.util.*;

class Solution {
    
    static Locate[] point;
    static char[][] maps;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public int solution(String[] map) {
        maps = new char[map.length][map[0].length()];
        point = new Locate[3];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                maps[i][j] = map[i].charAt(j);

                if (maps[i][j] == 'S') {
                    point[0] = new Locate(i, j);
                } else if (maps[i][j] == 'E') {
                    point[1] = new Locate(i, j);
                } else if (maps[i][j] == 'L') {
                    point[2] = new Locate(i, j);
                }
            }
        }

        //우선 레버까지 가는 길 찾기
        int toLever = bfs(point[0], point[2]);
        if (toLever == Integer.MAX_VALUE) return -1;
        //레버에서 출구까지 가는 길찾기
        int toEnd = bfs(point[2], point[1]);
        if (toEnd == Integer.MAX_VALUE) return -1;

        return toLever + toEnd;
    }

    private static int bfs(Locate start, Locate end) {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(start.row, start.col, 0));

        boolean[][] visited = new boolean[maps.length][maps[0].length];
        visited[start.row][start.col] = true;
        int answer = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            Locate curr = que.poll();

            if (curr.row == end.row && curr.col == end.col) {
                answer = Math.min(answer, curr.time);
            }

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || dcol < 0 || drow >= maps.length || dcol >= maps[0].length || maps[drow][dcol] == 'X' || visited[drow][dcol])
                    continue;

                visited[drow][dcol] = true;
                que.offer(new Locate(drow, dcol, curr.time + 1));
            }
        }
        return answer;
    }

    static class Locate {
        int row, col, time;

        public Locate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Locate(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
