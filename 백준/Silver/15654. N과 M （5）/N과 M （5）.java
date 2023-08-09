import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] nums;
    static int[] answer;
    static StringBuilder sb;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        answer = new int[m];
        visited = new boolean[n];
        permutation(0);
        System.out.println(sb);
    }

    private static void permutation(int idx) {
        if (idx == m) {
            for (int result : answer) {
                sb.append(result).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            answer[idx] = nums[i];
            permutation(idx + 1);
            visited[i] = false;
        }
    }
}
