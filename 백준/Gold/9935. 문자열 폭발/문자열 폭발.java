import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String bomb = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            stack.push(ch);

            //stack에 쌓인 문자열이 bomb의 길이보다 크거나 같을 때 새로들어온 문자열을 추가하여 비교
            if (stack.size() >= bomb.length()) {
                boolean check = false;
                for (int j = 0; j < bomb.length(); j++) {
                    if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        check = true;
                        break;
                    }
                }

                //폭탄이 존재한다면 없애기
                if (!check) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }


        if (stack.isEmpty()) sb.append("FRULA");
        else {
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            sb.reverse();
        }
        System.out.println(sb);
    }
}
