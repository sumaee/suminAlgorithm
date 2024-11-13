import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int sum = 0;
        int left = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, arr[i]);
            sum += arr[i];
        }

        while (left < sum) {
            int mid = (left + sum) / 2;

            if (check(arr, mid, n, m)) {
                left = mid + 1;
            } else {
                sum = mid;
            }
        }
        sb.append(left).append("\n");

        sum = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum > left) {
                sum = arr[i];
                m--;
                sb.append(cnt).append(" ");
                cnt = 1;
            } else {
                cnt++;
            }

            if (m == n - i) {
                break;
            }
        }

        while (m-- > 0) {
            sb.append(cnt).append(" ");
            cnt = 1;
        }
        System.out.println(sb);
    }

    private static boolean check(int[] arr, int mid, int n, int m) {
        int sum = 0;
        int cnt = 1;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum > mid) {
                cnt++;
                sum = arr[i];
            }
        }

        return cnt > m;
    }
}