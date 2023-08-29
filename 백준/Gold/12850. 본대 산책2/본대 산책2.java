import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final long MOD = 1000000007;
    static long[][] map = {
            {0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());

        long[][] answer = findAnswer(d);
        System.out.println(answer[0][0]);
    }

    private static long[][] findAnswer(int d) {
        if (d == 1) {
            return map;
        }

        long[][] tmp = findAnswer(d / 2);
        tmp = getMulty(tmp, tmp);
        if (d % 2 == 1) {
            tmp = getMulty(tmp, map);
        }
        return tmp;
    }

    private static long[][] getMulty(long[][] a, long[][] b) {
        long[][] result = new long[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return result;
    }
}
