import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        Stack<Character> stack;
        nxt:
        for (int tc = 0; tc < testCase; tc++) {
            stack = new Stack<>();
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                //만약 여는 괄호라면 그냥 넣기
                if (ch == '(') {
                    stack.push(ch);
                }
                //만약 닫는 괄호인데
                else {
                    //스택이 비어있으면 괄호 형식에 안맞는 것이므로 NO
                    if (stack.isEmpty()) {
                        sb.append("NO").append("\n");
                        continue nxt;
                    }
                    //스택이 비어있지 않으면 맨 위에있는 여는 괄호 하나 pop
                    stack.pop();
                }
            }

            //스택이 비어있지 않으면 괄호가 다 안빠진 것이므로 NO
            if (!stack.isEmpty()) sb.append("NO").append("\n");
            else sb.append("YES").append("\n");
        }
        System.out.println(sb);
    }
}
