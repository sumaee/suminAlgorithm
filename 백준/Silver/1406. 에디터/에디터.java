import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            leftStack.push(str.charAt(i));
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("L")) {
                if (!leftStack.isEmpty()) rightStack.push(leftStack.pop());
            } else if (command.equals("D")) {
                if (!rightStack.isEmpty()) leftStack.push(rightStack.pop());
            } else if (command.equals("B")) {
                if (!leftStack.isEmpty()) leftStack.pop();
            } else {
                leftStack.push(st.nextToken().charAt(0));
            }
        }

        while (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }

        while (!rightStack.isEmpty()) {
            sb.append(rightStack.pop());
        }
        System.out.println(sb);

    }
}
