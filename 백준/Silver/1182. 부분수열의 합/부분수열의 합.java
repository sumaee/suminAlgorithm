import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정수의 개수
        int s = Integer.parseInt(st.nextToken()); // 구하고자하는 합
        cnt = 0;
        visited = new boolean[n];
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            combination(i, 0, n, s);
        }
        System.out.println(cnt);
    }

    private static void combination(int limit, int idx, int n, int s) {
        if (limit == 0) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    sum += arr[i];
                }
            }

            if (sum == s) cnt++;
            return;
        }

        for (int i = idx; i < n; i++) {
            visited[i] = true;
            combination(limit - 1, i + 1, n, s);
            visited[i] = false;
        }
    }
}
