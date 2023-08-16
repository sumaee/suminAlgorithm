import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] board;
    static boolean[][][][] visisted;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        int redRow = 0, redCol = 0;
        int blueRow = 0, blueCol = 0;
        int endRow = 0, endCol = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);

                if (board[i][j] == 'R') {
                    redRow = i;
                    redCol = j;
                } else if (board[i][j] == 'B') {
                    blueRow = i;
                    blueCol = j;
                } else if (board[i][j] == 'O') {
                    endRow = i;
                    endCol = j;
                }
            }
        }

        System.out.println(bfs(redRow, redCol, blueRow, blueCol, endRow, endCol));

    }

    private static int bfs(int redRow, int redCol, int blueRow, int blueCol, int endRow, int endCol) {
        Queue<Marble> que = new LinkedList<>();
        que.offer(new Marble(redRow, redCol, 1, blueRow, blueCol));

        visisted = new boolean[n][m][n][m];
        visisted[redRow][redCol][blueRow][blueCol] = true;

        while (!que.isEmpty()) {
            Marble curr = que.poll();

            //10번이 넘으면 끝
            if (curr.redCnt > 10) {
                return -1;
            }

            //상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int redDrow = curr.redRow;
                int redDcol = curr.redCol;
                int blueDrow = curr.blueRow;
                int blueDcol = curr.blueCol;

                //이동후 탈출에 성공했는지 체크
                boolean red = false;
                boolean blue = false;

                //빨간 구슬 이동 -> 벽을 만날때까지 진행
                while (board[redDrow + dr[i]][redDcol + dc[i]] != '#') {
                    redDrow += dr[i];
                    redDcol += dc[i];

                    //이동했다가 구멍을 만나면 끝
                    if (redDrow == endRow && redDcol == endCol) {
                        red = true;
                        break;
                    }
                }

                //똑같이 파란 구슬 이동 -> 벽을 만날 때까지 진행
                while (board[blueDrow + dr[i]][blueDcol + dc[i]] != '#') {
                    blueDrow += dr[i];
                    blueDcol += dc[i];

                    //이동했다가 구멍을 만나면 끝
                    if (blueDrow == endRow && blueDcol == endCol) {
                        blue = true;
                        break;
                    }
                }

                //파란구슬이 빠졌다면 다른 경로 찾기
                if (blue) {
                    continue;
                }

                //빨간 구슬만 빠졌다면 성공
                if (red) {
                    return curr.redCnt;
                }

                //둘다 구멍에 안빠졌는데 만나는 위치가 같다면
                if (redDrow == blueDrow && redDcol == blueDcol) {
                    //위로 움직이는 중에 만났다면 시작 행이 더 큰 값이 아래로 와야함
                    if (i == 0) {
                        if (curr.redRow > curr.blueRow) {
                            redDrow -= dr[i];
                        } else {
                            blueDrow -= dr[i];
                        }
                    }
                    //오른쪽으로 움직이는 중에 만났다면 시작 열이 더 큰값이 오른쪽으로 와야함
                    else if (i == 1) {
                        if (curr.redCol > curr.blueCol) {
                            blueDcol -= dc[i];
                        } else {
                            redDcol -= dc[i];
                        }
                    }
                    //밑으로 움직이는 중에 만났다면 시작 행이 더 큰값이 아래로 와야함
                    else if (i == 2) {
                        if (curr.redRow > curr.blueRow) {
                            blueDrow -= dr[i];
                        } else {
                            redDrow -= dr[i];
                        }
                    }
                    //왼쪽으로 움직이는 중에 만났다면 시작 열이 더 큰값이 왼쪽으로 와야함
                    else {
                        if (curr.redCol > curr.blueCol) {
                            redDcol -= dc[i];
                        } else {
                            blueDcol -= dc[i];
                        }
                    }
                }

                //이동을 마친 후 두 구슬이 같이 방문하지 않은 곳이라면 추가
                if (!visisted[redDrow][redDcol][blueDrow][blueDcol]) {
                    visisted[redDrow][redDcol][blueDrow][blueDcol] = true;
                    que.offer(new Marble(redDrow, redDcol, curr.redCnt + 1, blueDrow, blueDcol));
                }
            }
        }

        //마지막까지 성공을 못했다면 실패
        return -1;
    }

    static class Marble {
        int redRow, redCol, redCnt, blueRow, blueCol;

        public Marble(int redRow, int redCol, int redCnt, int blueRow, int blueCol) {
            this.redRow = redRow;
            this.redCol = redCol;
            this.redCnt = redCnt;
            this.blueRow = blueRow;
            this.blueCol = blueCol;
        }
    }
}
