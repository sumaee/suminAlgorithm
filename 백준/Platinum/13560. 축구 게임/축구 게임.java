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
        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += arr[i];
            int checkSum = i * (i - 1) / 2;
            if (sum < checkSum) {
                System.out.println(-1);
                return;
            }
        }

        if (sum != n * (n - 1) / 2) {
            System.out.println(-1);
        } else {
            System.out.println(1);
        }
    }
}