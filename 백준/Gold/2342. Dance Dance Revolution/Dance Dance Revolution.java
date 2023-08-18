import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] move;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine().replace(" ", "");
        move = new int[line.length() - 1];
        for (int i = 0; i < move.length; i++) {
            move[i] = line.charAt(i) - '0';
        }

        dp = new int[5][5][line.length()];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int answer = findMin(0, 0, 0);
        System.out.println(answer);
    }

    private static int findMin(int left, int right, int cnt) {
        //움직임이 끝났으면 끝
        if (cnt == move.length) {
            return 0;
        }

        //이미 채워져 있으면 해당 값을 리턴
        if (dp[left][right][cnt] != -1) {
            return dp[left][right][cnt];
        }

        //최솟값 채우기
        dp[left][right][cnt] = Math.min(findMin(move[cnt], right, cnt + 1) + energy(left, move[cnt]), findMin(left, move[cnt], cnt + 1) + energy(right, move[cnt]));
        return dp[left][right][cnt];
    }

    private static int energy(int start, int end) {
        // 중앙에서 움직이는 거라면
        if (start == 0) return 2;

        int num = Math.abs(start - end);
        //제자리 움직임이라면
        if (num == 0) return 1;
        //인접한 부분으로 이동하는 거라면
        if (num == 1 || num == 3) return 3;
        //반대로이동하는 거라면
        return 4;
    }
}
