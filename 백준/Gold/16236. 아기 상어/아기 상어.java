import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, time;
    static int n, sharkRow, sharkCol, sharkSize, eatRow, eatCol, minTime;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                //상어의 초반 위치 기록
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    sharkRow = i;
                    sharkCol = j;
                }
            }
        }

        //초반 상어 크기는 2
        sharkSize = 2;
        //물고기를 잡아 먹을 때마다 계속 진행
        int count = 0; // 물고기 잡아먹은 횟수
        int answer = 0;
        while (true) {
            time = new int[n][n];
            eatRow = -1;
            eatCol = -1;
            minTime = Integer.MAX_VALUE;

            bfs(sharkRow, sharkCol);

            //탐색 진행후 먹을 수 있는 물고기 위치로 이동했다면
            if (eatRow != -1 && eatCol != -1) {
                count++;
                map[eatRow][eatCol] = 0;
                sharkRow = eatRow;
                sharkCol = eatCol;

                answer += time[eatRow][eatCol];

                //상어 크기가 업그레이드 조건을 만족한다면
                if (sharkSize == count) {
                    sharkSize++;
                    count = 0;
                }
            }
            //먹을 수있는 위치가 없다면
            else {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void bfs(int sharkRow, int sharkCol) {
        Queue<Locate> que = new LinkedList<>();
        que.offer(new Locate(sharkRow, sharkCol));

        while (!que.isEmpty()) {
            Locate curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 이미 한번 갔던 곳이라면 다음으로 진행
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || time[drow][dcol] != 0) continue;

                //상어가 이동할 수 있는 위치가 아니라면 패스
                if (map[drow][dcol] > sharkSize) continue;

                time[drow][dcol] = time[curr.row][curr.col] + 1;

                //만약 먹을 수 있는 물고기가 있다면
                if (map[drow][dcol] != 0 && map[drow][dcol] < sharkSize) {
                    //지금까지 움직인 시간이 더 작다면 갱신
                    if (minTime > time[drow][dcol]) {
                        minTime = time[drow][dcol];
                        eatRow = drow;
                        eatCol = dcol;
                    }
                    //최단 시간으로 먹을 수 있는 물고기가 많다면
                    else if (minTime == time[drow][dcol]) {
                        //높이가 같다면 왼쪽이 있는 물고기 먹기
                        if (eatRow == drow) {
                            if (eatCol > dcol) {
                                eatCol = dcol;
                            }
                        }
                        //높이가 다르다면 위에 있는 물고기 먹기
                        else if (eatRow > drow) {
                            eatRow = drow;
                            eatCol = dcol;
                        }
                    }

                }
                que.offer(new Locate(drow, dcol));
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
