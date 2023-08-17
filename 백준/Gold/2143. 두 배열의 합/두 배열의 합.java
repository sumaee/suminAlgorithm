import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        //A배열 받기
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        //B배열 받기
        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        //부분합 구하기
        for (int i = 1; i < n; i++) {
            a[i] += a[i - 1];
        }

        for (int i = 1; i < m; i++) {
            b[i] += b[i - 1];
        }

        //투포인터로 풀기위한 준비
        long[] aSum = new long[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int ai = a[j];
                if (i > 0) ai -= a[i - 1];
                aSum[idx++] = ai;
            }
        }

        long[] bSum = new long[m * (m + 1) / 2];
        idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int bi = b[j];
                if (i > 0) bi -= b[i - 1];
                bSum[idx++] = bi;
            }
        }

        //투포인터 풀이 시작
        Arrays.sort(aSum);
        Arrays.sort(bSum);
        int leftIdx = 0;
        int rightIdx = bSum.length - 1;
        long cnt = 0;
        while (leftIdx < aSum.length && rightIdx >= 0) {
            long sum = aSum[leftIdx] + bSum[rightIdx];

            if (sum == t) {
                long ac = 0;
                long value = aSum[leftIdx];
                while (leftIdx < aSum.length && aSum[leftIdx] == value) {
                    leftIdx++;
                    ac++;
                }

                long bc = 0;
                value = bSum[rightIdx];
                while (rightIdx >= 0 && bSum[rightIdx] == value) {
                    rightIdx--;
                    bc++;
                }

                cnt += ac * bc;
            }

            if (sum > t) {
                rightIdx--;
            } else if (sum < t) {
                leftIdx++;
            }
        }

        System.out.println(cnt);
    }
}
