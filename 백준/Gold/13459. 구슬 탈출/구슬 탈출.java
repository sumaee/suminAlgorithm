import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static Locate red, blue, end;
    static char[][] board;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
   public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());

       board = new char[n][m];
       for (int i = 0; i < n; i++) {
           String line = br.readLine();
           for (int j = 0; j < m; j++) {
               board[i][j] = line.charAt(j);

               if (board[i][j] == 'R') {
                   red = new Locate(i, j);
               } else if (board[i][j] == 'B') {
                   blue = new Locate(i, j);
               } else if (board[i][j] == 'O') {
                   end = new Locate(i, j);
               }
           }
       }

       System.out.println(bfs());
   }

    private static int bfs() {
        Queue<Bead> que = new LinkedList<>();
        que.offer(new Bead(red.row, red.col, 1, blue.row, blue.col));

        boolean[][][][] visited = new boolean[n][m][n][m];
        visited[red.row][red.col][blue.row][blue.col] = true;

        while (!que.isEmpty()) {
            Bead curr = que.poll();

            if (curr.redCnt > 10) {
                return 0;
            }

            for (int i = 0; i < 4; i++) {
                int redDrow = curr.redRow;
                int redDcol = curr.redCol;
                int blueDrow = curr.blueRow;
                int blueDcol = curr.blueCol;

                boolean redFlag = false;
                boolean blueFlag = false;

                while (board[redDrow + dr[i]][redDcol + dc[i]] != '#') {
                    redDrow += dr[i];
                    redDcol += dc[i];

                    if (redDrow == end.row && redDcol == end.col) {
                        redFlag = true;
                        break;
                    }
                }

                while (board[blueDrow + dr[i]][blueDcol + dc[i]] != '#') {
                    blueDrow += dr[i];
                    blueDcol += dc[i];

                    if (blueDrow == end.row && blueDcol == end.col) {
                        blueFlag = true;
                        break;
                    }
                }

                if (blueFlag) {
                    continue;
                }

                if (redFlag) {
                    return 1;
                }

                if (redDrow == blueDrow && redDcol == blueDcol) {
                    if (i == 0) {
                        if (curr.redRow > curr.blueRow) {
                            redDrow -= dr[i];
                        } else {
                            blueDrow -= dr[i];
                        }
                    } else if (i == 1) {
                        if (curr.redCol > curr.blueCol) {
                            blueDcol -= dc[i];
                        } else {
                            redDcol -= dc[i];
                        }
                    } else if (i == 2) {
                        if (curr.redRow > curr.blueRow) {
                            blueDrow -= dr[i];
                        } else {
                            redDrow -= dr[i];
                        }
                    } else {
                        if (curr.redCol > curr.blueCol) {
                            redDcol -= dc[i];
                        } else {
                            blueDcol -= dc[i];
                        }
                    }
                }

                if (!visited[redDrow][redDcol][blueDrow][blueDcol]) {
                    visited[redDrow][redDcol][blueDrow][blueDcol] = true;
                    que.offer(new Bead(redDrow, redDcol, curr.redCnt + 1, blueDrow, blueDcol));
                }
            }
        }
        return 0;
    }

   static class Locate{
       int row, col;

       public Locate(int row, int col) {
           this.row = row;
           this.col = col;
       }
   }

   static class Bead{
       int redRow, redCol, redCnt, blueRow, blueCol;

       public Bead(int redRow, int redCol, int redCnt, int blueRow, int blueCol) {
           this.redRow = redRow;
           this.redCol = redCol;
           this.redCnt = redCnt;
           this.blueRow = blueRow;
           this.blueCol = blueCol;
       }
   }
}