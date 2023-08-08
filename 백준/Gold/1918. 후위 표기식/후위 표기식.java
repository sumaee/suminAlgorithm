import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                //연산자의 우선순위가 스택에 들어가있는 우선순위보다 낮다면 높은게 나올때까지 뽑기
                while (!stack.isEmpty() && check(ch) <= check(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            }
            //만약 여는 괄호라면 그냥 넣기
            else if (ch == '(') {
                stack.push(ch);
            }

            //닫는 괄호라면 여는괄호가 나올때까지 뽑기
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                //여는 괄호 버리기
                stack.pop();
            }

            //그외 알파벳이라면 이어붙히기
            else {
                sb.append(ch);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

    private static int check(char ch) {
        if (ch == '(' || ch == ')') return 0;
        if (ch == '+' || ch == '-') return 1;
        if (ch == '*' || ch == '/') return 2;

        return -1;
    }
}
