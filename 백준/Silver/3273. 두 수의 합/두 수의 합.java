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
        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int answer = 0;
        int sum;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            sum = arr[left] + arr[right];

            if (sum == x) {
                answer++;
            }

            //만약 sum이 x보다 크다면 right 줄이기
            if (sum > x) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(answer);
    }
}
