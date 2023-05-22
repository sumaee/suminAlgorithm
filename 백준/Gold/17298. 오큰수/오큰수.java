import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Stack<Num> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int[] answer = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n ; i++){
            int num = Integer.parseInt(st.nextToken());
            // stack 이 비어있으면 넣기
            if (stack.isEmpty()) {
                stack.push(new Num(i, num));
                continue;
            }

            while (!stack.isEmpty() && stack.peek().num < num) {
                answer[stack.pop().idx] = num;
            }

            stack.push(new Num(i, num));
        }

        while (!stack.isEmpty()) {
            answer[stack.pop().idx] = -1;
        }

        for (Integer result : answer) {
            sb.append(result).append(" ");
        }

        System.out.println(sb);
    }
}

class Num{
    int idx;
    int num;

    public Num(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }
}