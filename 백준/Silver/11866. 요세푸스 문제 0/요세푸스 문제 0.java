import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int idx = 1;
        Deque<Integer> que = new ArrayDeque<>();
        Queue<Integer> answer = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            que.offer(i + 1);
        }

        while (!que.isEmpty()) {

            if (idx == k) {
                answer.offer(que.pollFirst());
                idx = 1;
                continue;
            }

            que.offer(que.pollFirst());
            idx++;
        }

        sb.append("<");
        while (!answer.isEmpty()) {
            sb.append(answer.poll());

            if (answer.size() != 0) {
                sb.append(", ");
            }
        }

        sb.append(">");
        System.out.println(sb);
    }
}