import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] rooms;
    static int r, c, airRow;
    static Queue<Locate> dust;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        //방정보를 입력 받으면서 미세먼지 확산시키기
        //공기 청정기가 있는 행 기록
        rooms = new int[r][c];
        airRow = -1;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                rooms[i][j] = Integer.parseInt(st.nextToken());

                //공기 청정기 위치 기록
                if (airRow == -1 && rooms[i][j] == -1) {
                    airRow = i;
                }
            }
        }

        for (int time = 0; time < t; time++) {
            //미세먼지 공간 확인
            checkDust();

            //퍼뜨리기
            spreadDust();

            //공기청정기 작동
            airCondition();
        }

        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rooms[i][j] == -1) continue;
                answer += rooms[i][j];
            }
        }
        System.out.println(answer);
    }

    private static void airCondition() {
        int overAir = airRow;
        int underAir = airRow + 1;

        //위쪽 공기청정기 작동
        for (int i = overAir - 1; i > 0; i--) {
            rooms[i][0] = rooms[i - 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            rooms[0][i] = rooms[0][i + 1];
        }
        for (int i = 0; i < overAir; i++) {
            rooms[i][c - 1] = rooms[i + 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            rooms[overAir][i] = rooms[overAir][i - 1];
        }
        rooms[overAir][1] = 0;

        //아랫쪽 작동
        for (int i = underAir + 1; i < r - 1; i++) {
            rooms[i][0] = rooms[i + 1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            rooms[r - 1][i] = rooms[r - 1][i + 1];
        }
        for (int i = r - 1; i > underAir; i--) {
            rooms[i][c - 1] = rooms[i - 1][c - 1];
        }
        for (int i = c - 1; i > 1; i--) {
            rooms[underAir][i] = rooms[underAir][i - 1];
        }
        rooms[underAir][1] = 0;
    }

    private static void checkDust() {
        dust = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rooms[i][j] == -1 || rooms[i][j] == 0) continue;

                dust.add(new Locate(i, j, rooms[i][j]));
            }
        }
    }

    private static void spreadDust() {
        while (!dust.isEmpty()) {
            Locate curr = dust.poll();

            //더 퍼뜨릴수 없다면 패스
            if (curr.w < 5) continue;

            int spread = curr.w / 5;
            int count = 0;

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 공기 청정기가 있다면 패스
                if (drow < 0 || dcol < 0 || drow >= r || dcol >= c || rooms[drow][dcol] == -1) continue;

                rooms[drow][dcol] += spread;
                count++;
            }

            rooms[curr.row][curr.col] -= count * spread;
        }
    }

    static class Locate {
        int row, col, w;

        public Locate(int row, int col, int w) {
            this.row = row;
            this.col = col;
            this.w = w;
        }
    }
}
