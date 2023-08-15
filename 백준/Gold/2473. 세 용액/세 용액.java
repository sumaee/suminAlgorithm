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
        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int small = 0;
        int center = 0;
        int big = 0;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    small = i;
                    center = left;
                    big = right;
                }

                if (sum >= 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println(arr[small] + " " + arr[center] + " " + arr[big]);
    }
}
