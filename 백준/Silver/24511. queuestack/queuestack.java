import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Deque<Integer> que = new ArrayDeque<>();

        StringTokenizer structures = new StringTokenizer(br.readLine());
        StringTokenizer values = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int structure = Integer.parseInt(structures.nextToken());
            int value = Integer.parseInt(values.nextToken());

            if (structure == 0) {
                que.offer(value);
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (m-- > 0) {
            int num = Integer.parseInt(st.nextToken());

            que.offerFirst(num);
            sb.append(que.pollLast()).append(" ");
        }

        System.out.println(sb);
    }
}