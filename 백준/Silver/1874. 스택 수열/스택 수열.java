import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        //숫자를 앞 숫자부터 빼기 위한 큐 선언
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        Stack<Integer> stack = new Stack<>();
        boolean flag = true;
        out:
        for (int i = 0; i < n; i++) {
            int check = Integer.parseInt(br.readLine());

            //스택의 맨 윗 숫자가 뽑아야할 숫자가 될 때까지 큐에서 빼서 넣기
            while (true) {
                //만약 스택 맨 위의 숫자가 뽑아야할 숫자가 되면 뽑기
                if (!stack.isEmpty() && stack.peek() == check) {
                    stack.pop();
                    sb.append("-").append("\n");
                    break;
                }

                //넣어야하는데 큐가 비어있으면
                if (queue.isEmpty()) {
                    flag = false;
                    break out;
                } else {
                    stack.push(queue.poll());
                    sb.append("+").append("\n");
                }
            }
        }

        if (flag) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
    }
}
