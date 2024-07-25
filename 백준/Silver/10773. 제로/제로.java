import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        int zeroCnt = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num != 0) {
                for (int j = zeroCnt; j > 0; j--, zeroCnt--) {
                    stack.pop();
                }
                stack.push(num);
            } else {
                zeroCnt++;
            }
        }

        for (int i = 0; i < zeroCnt; i++) {
            stack.pop();
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        System.out.println(sum);
    }
}