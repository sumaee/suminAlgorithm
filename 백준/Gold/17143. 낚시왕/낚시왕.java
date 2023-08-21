import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int r, c, m;
    static Shark[][] map;
    static Queue<Shark> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //상어가 없다면 잡을 게 없음
        if (m == 0) {
            System.out.println(0);
            return;
        }

        //상어의 정보 받기
        map = new Shark[r][c];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            //위치 변환을 쉽게 하기 위해 상하우좌(1, 2, 3, 4)를 상좌하우(0, 1, 2, 3)으로 바꾸기
            if (d == 1) {
                d = 0;
            } else if (d == 4) {
                d = 1;
            }

            map[row - 1][col - 1] = new Shark(row - 1, col - 1, s, d, z);
        }

        int answer = 0;
        //낚시 시작
        for (int col = 0; col < c; col++) {
            //상어 잡기
            for (int row = 0; row < r; row++) {
                //가장 가까운 상어 잡기
                if (map[row][col] != null) {
                    answer += map[row][col].z;
                    map[row][col] = null;
                    break;
                }
            }

            //상어 이동하기
            que = new LinkedList<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    //상어가 있는 칸들 큐에 추가
                    if (map[i][j] != null) {
                        que.offer(map[i][j]);
                    }
                }
            }

            //상어가 이동한 후 새로운 낚시장을 만들기
            moveShark();
        }
        System.out.println(answer);
    }

    private static void moveShark() {
        map = new Shark[r][c];
        while (!que.isEmpty()) {
            Shark curr = que.poll();

            //나머지 연산을 통해 마지막 위치 계산
            int speed = curr.s;
            //좌우로 이동하는 상어라면
            if (curr.d == 1 || curr.d == 3) {
                speed %= (c - 1) * 2;
            }
            //상하로 이동하는 상어라면
            else if (curr.d == 0 || curr.d == 2) {
                speed %= (r - 1) * 2;
            }

            for (int i = 0; i < speed; i++) {
                int drow = curr.row + dr[curr.d];
                int dcol = curr.col + dc[curr.d];

                //이동 위치가 범위를 벗어난다면
                if (drow < 0 || drow >= r || dcol < 0 || dcol >= c) {
                    //범위가 안벗어나게 한 후 방향을 반대로 돌리기
                    curr.row -= dr[curr.d];
                    curr.col -= dc[curr.d];
                    curr.d = (curr.d + 2) % 4;
                    continue;
                }

                curr.row = drow;
                curr.col = dcol;
            }

            //최종 위치에서 상어가 존재하는지 아닌지 확인
            if (map[curr.row][curr.col] != null) {
                //상어 크기 비교해서 더 큰값 넣기
                if (map[curr.row][curr.col].z < curr.z) {
                    map[curr.row][curr.col] = new Shark(curr.row, curr.col, curr.s, curr.d, curr.z);
                }
            } else {
                map[curr.row][curr.col] = new Shark(curr.row, curr.col, curr.s, curr.d, curr.z);
            }
        }
    }

    static class Shark {
        int row, col, s, d, z;

        public Shark(int row, int col, int s, int d, int z) {
            this.row = row;
            this.col = col;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
