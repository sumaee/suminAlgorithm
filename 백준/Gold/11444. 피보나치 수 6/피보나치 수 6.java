import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    final static long MOD = 1000000007;
    final static long[][] init = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long[][] nums = {{1, 1}, {1, 0}};

        System.out.println(findAnswer(nums, n - 1)[0][0]);
    }

    private static long[][] findAnswer(long[][] nums, long idx) {
        if (idx == 1 || idx == 0) {
            return nums;
        }

        long[][] halfArr = findAnswer(nums, idx / 2);

        halfArr = cal(halfArr, halfArr);

        if (idx % 2 == 1) {
            halfArr = cal(halfArr, init);
        }

        return halfArr;
    }

    private static long[][] cal(long[][] a, long[][] b) {
        long[][] result = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                    result[i][j] %= MOD;
                }
            }
        }

        return result;
    }
}
