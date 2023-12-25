import java.lang.*;
import java.util.*;

class Solution {
    
    static int count;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        visited = new boolean[m][n];
        count = 0;

        int areaCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j, picture);
                    areaCnt++;
                }
            }
        }

        answer[0] = areaCnt;
        answer[1] = count;
        return answer;
    }
    
     private static void bfs(int row, int col, int[][] picture) {
        Queue<Node> que = new LinkedList<>();

        que.offer(new Node(row, col));
        visited[row][col] = true;

        int cnt = 1;
        while (!que.isEmpty()) {
            Node curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || dcol < 0 || drow >= picture.length || dcol >= picture[0].length || visited[drow][dcol] || picture[drow][dcol] != picture[row][col]) {
                    continue;
                }

                que.offer(new Node(drow, dcol));
                visited[drow][dcol] = true;
                cnt++;
            }
        }

        count = Math.max(count, cnt);
    }
    
    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}