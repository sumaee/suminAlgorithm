import java.lang.*;
import java.util.*;

class Solution {
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int solution(String[] board) {
        Node start = new Node();
        Node end = new Node();
        char[][] map = new char[board.length][board[0].length()];
        for (int i = 0; i < map.length; i++) {
            String str = board[i];
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'R') {
                    start.row = i;
                    start.col = j;
                }

                if (map[i][j] == 'G') {
                    end.row = i;
                    end.col = j;
                }
            }
        }

        return bfs(start, end, map);
    }
    
    private static int bfs(Node start, Node end, char[][] map) {
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        que.offer(new Node(start.row, start.col, 0));
        visited[start.row][start.col] = true;
        while (!que.isEmpty()) {
            Node curr = que.poll();


            //현재 위치에서 상하좌우로 미끄러져 벽이나 장애물이 있는 곳까지 가기
            for (int i = 0; i < 4; i++) {
                int drow = curr.row;
                int dcol = curr.col;

                //도착 위치라면 끝
                if (curr.row == end.row && curr.col == end.col) {
                    return curr.cnt;
                }

                while (drow + dr[i] >= 0 && dcol + dc[i] >= 0 && drow + dr[i] < map.length && dcol + dc[i] < map[0].length && map[drow + dr[i]][dcol + dc[i]] != 'D') {
                    drow += dr[i];
                    dcol += dc[i];
                }

                if (visited[drow][dcol]) continue;
                visited[drow][dcol] = true;
                que.offer(new Node(drow, dcol, curr.cnt + 1));
            }
        }

        return -1;
    }
    
    static class Node {
        int row, col, cnt;

        public Node(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        public Node() {
        }
    }

}