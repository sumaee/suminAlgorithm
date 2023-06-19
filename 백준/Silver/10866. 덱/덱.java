import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        Deque<Integer> que = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            if (command.equals("push_front")) {
                que.offerFirst(Integer.parseInt(st.nextToken()));
                continue;
            } else if (command.equals("push_back")) {
                que.offerLast(Integer.parseInt(st.nextToken()));
                continue;
            } else if (command.equals("pop_front")) {
                if (que.isEmpty()) sb.append(-1);
                else sb.append(que.pollFirst());
            } else if (command.equals("pop_back")) {
                if (que.isEmpty()) sb.append(-1);
                else sb.append(que.pollLast());
            } else if (command.equals("size")) {
                sb.append(que.size());
            } else if (command.equals("empty")) {
                if (que.isEmpty()) sb.append(1);
                else sb.append(0);
            } else if (command.equals("front")) {
                if (que.isEmpty()) sb.append(-1);
                else sb.append(que.peekFirst());
            } else {
                if (que.isEmpty()) sb.append(-1);
                else sb.append(que.peekLast());
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}