import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        int aIdx = 0;
        int bIdx = 0;
        while (bIdx < n && aIdx < n) {
            if (arr[aIdx] - arr[bIdx] < m) {
                aIdx++;
            } else {
                answer = Math.min(answer, arr[aIdx] - arr[bIdx]);
                bIdx++;
            }
        }

        System.out.println(answer);
    }
}