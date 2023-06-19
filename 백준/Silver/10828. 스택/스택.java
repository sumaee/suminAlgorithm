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

        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            switch (command) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    continue;
                case "pop":
                    if (stack.isEmpty()) sb.append(-1);
                    else sb.append(stack.pop());
                    break;
                case "size":
                    sb.append(stack.size());
                    break;
                case "empty":
                    if (stack.isEmpty()) sb.append(1);
                    else sb.append(0);
                    break;
                default:
                    if (stack.isEmpty()) sb.append(-1);
                    else sb.append(stack.peek());
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}
