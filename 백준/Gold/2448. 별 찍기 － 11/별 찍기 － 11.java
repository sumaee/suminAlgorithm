import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        stars = new String[n][n * 2 - 1];
        getStar(n, 0, n - 1);
        for (String[] star : stars) {
            for (String s : star) {
                if (s == null) sb.append(" ");
                else sb.append(s);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void getStar(int n, int row, int col) {
        //제일 작은 삼각형을 만들수 있으면 만들기
        if (n == 3) {
            //맨 윗줄
            stars[row][col] = "*";
            //두번째줄
            stars[row + 1][col - 1] = stars[row + 1][col + 1] = "*";
            //마지막줄
            for (int i = 0; i <= 2; i++) {
                stars[row + 2][col - i] = "*";
                stars[row + 2][col + i] = "*";
            }
            return;
        }

        getStar(n / 2, row, col);
        getStar(n / 2, row + n / 2, col - n / 2);
        getStar(n / 2, row + n / 2, col + n / 2);
    }
}
