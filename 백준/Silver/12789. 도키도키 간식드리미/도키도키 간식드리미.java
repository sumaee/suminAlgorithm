import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int idx = 1;

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            if (num == idx) {
                idx++;
                continue;
            }
            
            if (!stack.isEmpty() && stack.peek() == idx) {
                stack.pop();
                idx++;
                i--;
            } else {
                stack.push(num);
            }
        }

        boolean flag = true;
        for (int i = 0; i < stack.size(); i++) {
            int nxt = stack.pop();

            if (nxt == idx) {
                idx++;
            } else {
                flag = false;
                break;
            }
        }

        System.out.println(flag ? "Nice" : "Sad");
    }
}