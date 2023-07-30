import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    final static int MOD = 1000;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int[][] nums = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] answer = findAnswer(nums.clone(), b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(answer[i][j] % MOD).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static int[][] findAnswer(int[][] nums, long idx) {
        //지수가 1이라면 그냥 리턴
        if (idx == 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    nums[i][j] %= MOD;
                }
            }
            return nums;
        }

        //지수의 절반 값 구하기
        int[][] halfArr = findAnswer(nums.clone(), idx / 2);


        //행렬의 제곱 구해주기
        halfArr = cal(halfArr, halfArr);

        //지수가 홀수면 nums와의 곱 리턴
        if (idx % 2 == 1) {
            halfArr = cal(halfArr, nums);
            return halfArr;
        }

        return halfArr;
    }

    private static int[][] cal(int[][] a, int[][] b) {
        int[][] result = new int[n][n];
        //행렬의 제곱 구해주기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += (a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return result;
    }


}
