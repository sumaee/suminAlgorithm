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
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        int leftValue = 0;
        int left = 0;
        int rightValue = 0;
        int right = n - 1;
        int sum;
        while (left < right) {
            sum = arr[right] + arr[left];

            if (Math.abs(sum) < answer) {
                answer = Math.abs(sum);
                leftValue = arr[left];
                rightValue = arr[right];
            }

            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(leftValue + " " + rightValue);
    }
}
