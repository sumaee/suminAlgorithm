import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] boards;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        boards = new char[n][m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                boards[i][j] = input.charAt(j);
            }
        }

        int blackStart = checkSum('B');
        int whiteStart = checkSum('W');

        System.out.println(Math.min(blackStart, whiteStart));
    }

    private static int checkSum(char startColor) {
        int[][] init = new int[n][m];
        int[][] sum = new int[n + 1][m + 1];
        //시작 색깔과 같은지 다른지 체스판 규칙대로 1(다름), 0(같음) 채우기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + j) % 2 == 0) {
                    init[i][j] = boards[i][j] == startColor ? 0 : 1;
                } else {
                    init[i][j] = boards[i][j] == startColor ? 1 : 0;
                }
            }
        }

        //누적합 구하기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] + init[i - 1][j - 1] - sum[i - 1][j - 1];
            }
        }

        //k만큼의 구간에서 최솟값 구하기
        int min = Integer.MAX_VALUE;
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                int total = sum[i][j] - sum[i - k][j] - sum[i][j - k] + sum[i - k][j - k];

                min = Math.min(min, total);
            }
        }
        return min;
    }
}