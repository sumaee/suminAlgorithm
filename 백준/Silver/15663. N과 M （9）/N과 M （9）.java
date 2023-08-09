import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static String[] nums, temp;
    static List<String> answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new String[n];
        int[] input = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        for (int i = 0; i < n; i++) {
            nums[i] = String.valueOf(input[i]);
        }

        answer = new ArrayList<>();
        temp = new String[m];
        visited = new boolean[n];
        permutation(0);

        for (String result : answer) {
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void permutation(int idx) {
        if (idx == m) {
            StringBuilder sb = new StringBuilder();
            for (String result : temp) {
                sb.append(result).append(" ");
            }
            if (!answer.contains(sb.toString())) {
                answer.add(sb.toString());
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            temp[idx] = nums[i];
            permutation(idx + 1);
            visited[i] = false;
        }
    }
}
