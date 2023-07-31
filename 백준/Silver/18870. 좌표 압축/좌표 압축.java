import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sort = arr.clone();
        Arrays.sort(sort);

        Map<Integer, Integer> rank = new HashMap<>();
        int idx = 0;
        for (int num : sort) {
            if (!rank.containsKey(num)) {
                rank.put(num, idx++);
            }
        }

        for (int num : arr) {
            sb.append(rank.get(num)).append(" ");
        }
        System.out.println(sb);

    }
}
