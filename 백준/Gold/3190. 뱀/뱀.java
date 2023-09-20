import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;
    static int n, k, l;
    static HashMap<Integer, String> dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 보드 크기
        k = Integer.parseInt(br.readLine()); // 사과의 개수

        //사과 위치 기록
        map = new int[n][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            map[row][col] = 1;
        }

        //이동 내용 기록
        l = Integer.parseInt(br.readLine()); // 이동
        dir = new HashMap<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            dir.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        System.out.println(checkTime());
    }

    private static int checkTime() {
        int time = 0;
        int currR = 0;
        int currC = 0;
        int d = 0;

        //뱀의 몸통이 있는 부분을 기록
        List<Locate> snake = new ArrayList<>();
        snake.add(new Locate(currR, currC));

        //시간이 흐를 때마다 확인
        while (true) {
            //시간이 흐름
            time++;

            //다음 칸 이동
            int drow = currR + dr[d];
            int dcol = currC + dc[d];

            //이동하는 칸의 범위 확인 -> 벗어난다면 현재 시간 리턴
            if (drow < 0 || dcol < 0 || drow >= n || dcol >= n) {
                return time;
            }

            //이동하는 칸에 몸통이 있다면 현재 시간 리턴
            for (Locate body : snake) {
                if (body.row == drow && body.col == dcol) {
                    return time;
                }
            }

            //사과가 있는 위치인지 확인
            if (map[drow][dcol] == 1) {
                //사과가 있다면 꼬리는 그대로 두고 몸통늘리기
                snake.add(new Locate(drow, dcol));
                //먹은 사과 없애기
                map[drow][dcol] = 0;
            } else {
                //사과가 없다면 꼬리도 이동
                snake.remove(0);
                snake.add(new Locate(drow, dcol));
            }

            //방향을 바꿔야하는 시간인지 확인
            if (dir.containsKey(time)) {
                //오른쪽으로 90도
                if (dir.get(time).equals("D")) {
                    d++;
                    d %= 4;
                } else {
                    d--;
                    if (d == -1) {
                        d = 3;
                    }
                }
            }

            //현재 위치 변경
            currR = drow;
            currC = dcol;
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
