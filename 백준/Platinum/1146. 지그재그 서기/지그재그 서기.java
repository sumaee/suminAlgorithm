import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 1000000;
    static long[][] ascDp, descDp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        if (n == 1) System.out.println(1);
        else if (n == 2) {
            System.out.println(2);
        } else {
            ascDp = new long[n][n];
            descDp = new long[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ascDp[i][j] = -1;
                    descDp[i][j] = -1;
                }
            }

            ascDp[1][0] = ascDp[1][1] = ascDp[1][2] = 1;
            descDp[0][1] = descDp[1][1] = descDp[2][1] = 1;

            long answer = 0;
            for (int i = 0; i < n; i++) {
                answer += findBigger(n - i - 1, i) + findSmaller(n - i - 1, i);
                answer %= MOD;
            }
            System.out.println(answer);
        }

    }

    private static long findBigger(int big, int small) {
        if (ascDp[big][small] != -1) return ascDp[big][small];
        //더 큰 수가 없다면 종료
        if (big == 0) return 0;

        long result = 0;
        for (int i = 0; i < big; i++) {
            result += findSmaller(big - i - 1, small + i);
            result %= MOD;
        }
        return ascDp[big][small] = result;
    }

    private static long findSmaller(int big, int small) {
        if (descDp[big][small] != -1) return descDp[big][small];
        //더 작은 수가 없다면 종료
        if (small == 0) return 0;

        long result = 0;
        for (int i = 0; i < small; i++) {
            result += findBigger(small + big - i - 1, i);
            result %= MOD;
        }

        return descDp[big][small] = result;
    }


}
