import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static List<Cctv> cctvs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        cctvs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                //cctv가 있는 위치 기록
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new Cctv(i, j, map[i][j]));
                }
            }
        }

        //cctv위치들 전부 dfs돌기
        answer = Integer.MAX_VALUE;
        dfs(0, map);
        System.out.println(answer);
    }

    private static void dfs(int idx, int[][] map) {
        if (idx == cctvs.size()) {
            answer = Math.min(answer, count(map));
            return;
        }

        int row = cctvs.get(idx).row;
        int col = cctvs.get(idx).col;
        int[][] copy;

        //1번 cctv라면 상/하/좌/우 한쪽씩 확인하고 다음으로 넘기기
        if (cctvs.get(idx).num == 1) {
            //상 확인
            copy = copyMap(map);
            checkUp(copy, row, col);
            dfs(idx + 1, copy);

            //하 확인
            copy = copyMap(map);
            checkDown(copy, row, col);
            dfs(idx + 1, copy);

            //좌 확인
            copy = copyMap(map);
            checkLeft(copy, row, col);
            dfs(idx + 1, copy);

            //우 확인
            copy = copyMap(map);
            checkRight(copy, row, col);
            dfs(idx + 1, copy);
        }

        //2번 cctv라면 좌우 / 상하로 나눠서 확인하고 다음으로 넘기기
        else if (cctvs.get(idx).num == 2) {
            //좌우 확인
            copy = copyMap(map);
            checkLeft(copy, row, col);
            checkRight(copy, row, col);
            dfs(idx + 1, copy);

            //상하 확인
            copy = copyMap(map);
            checkUp(copy, row, col);
            checkDown(copy, row, col);
            dfs(idx + 1, copy);
        }

        //3번 cctv라면 상우 / 하좌 / 상좌 / 하우 나눠서 확인하고 다음으로 넘기기
        else if (cctvs.get(idx).num == 3) {
            //상우 확인
            copy = copyMap(map);
            checkUp(copy, row, col);
            checkRight(copy, row, col);
            dfs(idx + 1, copy);

            //하좌 확인
            copy = copyMap(map);
            checkLeft(copy, row, col);
            checkDown(copy, row, col);
            dfs(idx + 1, copy);

            //상좌 확인
            copy = copyMap(map);
            checkUp(copy, row, col);
            checkLeft(copy, row, col);
            dfs(idx + 1, copy);

            //하우 확인
            copy = copyMap(map);
            checkDown(copy, row, col);
            checkRight(copy, row, col);
            dfs(idx + 1, copy);
        }

        //4번 cctv라면 좌상우 / 좌하우 / 상좌하 / 상우하 나눠서 확인하고 다음으로 넘기기
        else if (cctvs.get(idx).num == 4) {
            //좌상우 확인
            copy = copyMap(map);
            checkLeft(copy, row, col);
            checkUp(copy, row, col);
            checkRight(copy, row, col);
            dfs(idx + 1, copy);

            //좌하우 확인
            copy = copyMap(map);
            checkLeft(copy, row, col);
            checkDown(copy, row, col);
            checkRight(copy, row, col);
            dfs(idx + 1, copy);

            //상좌하
            copy = copyMap(map);
            checkUp(copy, row, col);
            checkLeft(copy, row, col);
            checkDown(copy, row, col);
            dfs(idx + 1, copy);

            //상우하 확인
            copy = copyMap(map);
            checkUp(copy, row, col);
            checkRight(copy, row, col);
            checkDown(copy, row, col);
            dfs(idx + 1, copy);
        }

        //5번 cctv라면 상하좌우 확인하고 넘기기
        else {
            copy = copyMap(map);
            checkUp(copy, row, col);
            checkRight(copy, row, col);
            checkDown(copy, row, col);
            checkLeft(copy, row, col);
            dfs(idx + 1, copy);
        }
    }

    private static void checkUp(int[][] map, int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            //벽이면 끝
            if (map[i][col] == 6) return;
            //다른 cctv라면 건너뛰기
            if (map[i][col] != 0) continue;
            //빈칸이라면 -1로 변환
            map[i][col] = -1;
        }
    }

    private static void checkDown(int[][] map, int row, int col) {
        for (int i = row + 1; i < n; i++) {
            //벽이면 끝
            if (map[i][col] == 6) return;
            //다른 cctv라면 건너뛰기
            if (map[i][col] != 0) continue;
            //빈칸이라면 -1로 변환
            map[i][col] = -1;
        }
    }

    private static void checkLeft(int[][] map, int row, int col) {
        for (int i = col - 1; i >= 0; i--) {
            //벽이면 끝
            if (map[row][i] == 6) return;
            //다른 cctv라면 건너뛰기
            if (map[row][i] != 0) continue;
            //빈칸이라면 -1로 변환
            map[row][i] = -1;
        }
    }

    private static void checkRight(int[][] map, int row, int col) {
        for (int i = col + 1; i < m; i++) {
            //벽이면 끝
            if (map[row][i] == 6) return;
            //다른 cctv라면 건너뛰기
            if (map[row][i] != 0) continue;
            //빈칸이라면 -1로 변환
            map[row][i] = -1;
        }
    }

    private static int count(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[n][m];

        for (int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }

        return copy;
    }

    static class Cctv {
        int row, col, num;

        public Cctv(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }
    }
}