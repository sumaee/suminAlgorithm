import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static String answer;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = 1;
        int max = n;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (check(mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int mid) {
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        int curr = arr[0];

        for (int i = 1; i < k; i++) {
            if (cnt == m) {
                sb.append(0);
                continue;
            }

            if (arr[i] - curr >= mid) {
                sb.append(1);
                cnt++;
                curr = arr[i];
            } else {
                sb.append(0);
            }
        }

        if (cnt == m) {
            answer = sb.toString();
            return true;
        }

        return false;
    }
}