import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if (num == 1) {
                stack.push(Integer.parseInt(st.nextToken()));
            } else if (num == 2) {
                if (stack.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(stack.pop());
                }
            } else if (num == 3) {
                sb.append(stack.size());
            } else if (num == 4) {
                if (stack.isEmpty()) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
            } else {
                if (stack.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(stack.peek());
                }
            }

            if (num != 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}