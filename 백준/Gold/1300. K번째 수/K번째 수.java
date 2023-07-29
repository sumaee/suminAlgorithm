import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int left = 0;
        int right = k;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                cnt += Math.min(n, mid / i);
            }

            if (cnt >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}
