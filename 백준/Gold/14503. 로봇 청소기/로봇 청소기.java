import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (true) {
            //현재칸이 청소되어있지 않다면 청소
            if (map[r][c] == 0) {
                map[r][c] = -1;
                cnt++;
            }

            //4칸 확인
            //청소 안한 빈칸이 있다면
            if (check(r, c)) {
                //회전
                d--;
                if (d < 0) d = 3;

                //앞쪽이 청소되지 않았다면 청소
                if (map[r + dr[d]][c + dc[d]] == 0) {
                    r += dr[d];
                    c += dc[d];
                }
            }
            //청소안한 빈칸이 없다면
            else {
                //후진할 수있는 지 확인
                if (map[r + dr[(d + 2) % 4]][c + dc[(d + 2) % 4]] != 1) {
                    r += (dr[(d + 2) % 4]);
                    c += (dc[(d + 2) % 4]);
                } else {
                    break;
                }
            }
        }
        System.out.println(cnt);

    }

    //4칸 확인
    private static boolean check(int row, int col) {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            if (map[drow][dcol] == 0) {
                flag = true;
                break;
            }
        }

        return flag;
    }
}
