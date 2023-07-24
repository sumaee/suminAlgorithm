import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> que = new ArrayDeque<>();
        int sum = 0;
        int len = n + 1;
        for (int i = 0; i < n; i++) {
            que.offer(arr[i]);
            sum += arr[i];

            if (sum >= s) {
                while (sum >= s && !que.isEmpty()) {
                    len = Math.min(len, que.size());
                    sum -= que.pollFirst();
                }
            }
        }

        System.out.println(len == n + 1 ? 0 : len);
    }
}
