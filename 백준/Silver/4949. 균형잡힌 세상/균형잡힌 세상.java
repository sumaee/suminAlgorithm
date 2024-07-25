import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        next:
        while (true) {
            String str = br.readLine();

            if (str.equals(".")) {
                break;
            }

            str = str.replaceAll("[a-zA-Z0-9]", "-")
                    .replaceAll("-", "")
                    .replaceAll("\\.", "")
                    .replaceAll(" ", "");

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '[' || ch == '(') {
                    stack.push(ch);
                } else if (ch == ']' && !stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    sb.append("no").append("\n");
                    continue next;
                }
            }

            if (stack.isEmpty())
                sb.append("yes").append("\n");
            else
                sb.append("no").append("\n");
        }

        System.out.println(sb);
    }
}