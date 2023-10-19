import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] buildings = new int[n];

        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
                int out = stack.pop();
                answer[out] = i - out - 1;
            }
            stack.push(i);
        }

        int end = stack.pop();
        while (!stack.isEmpty()) {
            int out = stack.pop();
            answer[out] = end - out;
        }

        long sum = 0;
        for (long tmp : answer) {
            sum += tmp;
        }

        System.out.println(sum);
    }
}