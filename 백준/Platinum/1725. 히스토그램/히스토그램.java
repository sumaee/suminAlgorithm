import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //n == 1이라면 해당 수가 답
        if (n == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }

        long[] heights = new long[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        Stack<Block> stack = new Stack<>();
        stack.push(new Block(0, heights[0]));
        long answer = 0;

        for (int i = 1; i < n; i++) {
            int width = 0;

            while (!stack.isEmpty() && stack.peek().height > heights[i]) {
                Block block = stack.pop();
                width = i - block.idx;
                answer = Math.max(answer, width * block.height);
            }
            stack.push(new Block(i - width, heights[i]));
        }

        int h = 0;
        while (!stack.isEmpty()) {
            Block block = stack.pop();
            int width = n - block.idx;
            answer = Math.max(answer, width * block.height);
        }
        System.out.println(answer);
    }

    static class Block {
        int idx;
        long height;

        public Block(int idx, long height) {
            this.idx = idx;
            this.height = height;
        }
    }

}