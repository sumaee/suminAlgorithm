import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, answer;
    static int[][] paperArr;
    static boolean[][] sliceDir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paperArr = new int[n][m];
        sliceDir = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < m; j++) {
                paperArr[i][j] = temp.charAt(j) - '0';
            }
        }


        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        if (x == n) {
            getSum();
            return;
        }

        if (y == m) {
            dfs(x + 1, 0);
            return;
        }

        sliceDir[x][y] = true; // 세로로 자른 경우
        dfs(x, y + 1);
        sliceDir[x][y] = false; // 가로로 자른 경우
        dfs(x, y + 1);
    }

    private static void getSum() {
        int sum = 0;

        //세로로 자른 종이 합 구하기
        for (int i = 0; i < m; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                if (sliceDir[j][i]) {
                    temp *= 10;
                    temp += paperArr[j][i];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }

        //가로로 자른 종아 합 구하기
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < m; j++) {
                if (!sliceDir[i][j]) {
                    temp *= 10;
                    temp += paperArr[i][j];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }

        answer = Math.max(answer, sum);
    }
}