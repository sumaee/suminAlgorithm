import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] ab = new int[n * n];
        int[] cd = new int[n * n];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[idx] = arr[i][0] + arr[j][1];
                cd[idx++] = arr[i][2] + arr[j][3];
            }
        }

        Arrays.sort(ab);

        long answer = 0;
        for (int num : cd) {
            int upper = checkUpper(ab, -num);
            int lower = checkLower(ab, -num);
            answer += (upper - lower);
        }

        System.out.println(answer);
    }

    private static int checkUpper(int[] ab, int cdNum) {
        int left = 0;
        int right = ab.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (ab[mid] <= cdNum) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static int checkLower(int[] ab, int cdNum) {
        int left = 0;
        int right = ab.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (ab[mid] < cdNum) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
