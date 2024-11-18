import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        cnt = new int[10];
        int start = 1;
        int num = 1;
        while (start <= n) {
            while (start % 10 != 0 && start <= n) {
                getCount(start, num);
                start++;
            }
            while (n % 10 != 9 && n >= start) {
                getCount(n, num);
                n--;
            }

            if (start > n) break;

            for (int i = 0; i < 10; i++) {
                cnt[i] += (n / 10 - start / 10 + 1) * num;
            }
            num *= 10;
            start /= 10;
            n /= 10;

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(cnt[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void getCount(int value, int num) {
        while (value > 0) {
            cnt[value % 10] += num;
            value /= 10;
        }
    }
}