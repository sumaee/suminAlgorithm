import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int trees[];
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        trees = new int[n];
        st = new StringTokenizer(br.readLine());
        long min = 0;
        long max = 0;

        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        Arrays.sort(trees);
        long answer = 0;
        while (min <= max) {
            long mid = (min + max) / 2;

            long tree = cutTree(mid);

            if (tree < m) {
                max = mid - 1;
            } else {
                min = mid + 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }

    private static long cutTree(long mid) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (trees[i] > mid) {
                sum += (trees[i] - mid);
            }
        }
        return sum;
    }
}