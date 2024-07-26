import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                que.offerFirst(Integer.parseInt(st.nextToken()));
            } else if (command == 2) {
                que.offerLast(Integer.parseInt(st.nextToken()));
            } else if (command == 3) {
                if (que.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(que.pollFirst());
                }
            } else if (command == 4) {
                if (que.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(que.pollLast());
                }
            } else if (command == 5) {
                sb.append(que.size());
            } else if (command == 6) {
                sb.append(que.isEmpty() ? 1 : 0);
            } else if (command == 7) {
                if (que.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(que.peekFirst());
                }
            } else if (command == 8) {

                if (que.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(que.peekLast());
                }
            }

            if (command != 1 && command != 2) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}