import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            //0이라면 끝
            if (n == 0) {
                break;
            }

            //1이라면 해당 수가 답
            if (n == 1) {
                sb.append(Integer.parseInt(st.nextToken())).append("\n");
                continue;
            }

            //그 외의 개수라면 입력 받기
            long[] heights = new long[n];

            for (int i = 0; i < n; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            Stack<Block> stack = new Stack<>();
            stack.push(new Block(0, heights[0]));
            long answer = 0;
            for (int i = 1; i < n; i++) {
                int cnt = 0;

                while (!stack.isEmpty() && stack.peek().height > heights[i]) {

                    Block block = stack.pop();
                    cnt = i - block.idx;
                    answer = Math.max(answer, cnt * block.height);
                }

                stack.push(new Block(i - cnt, heights[i]));
            }

            int h = 0;
            while (!stack.isEmpty()) {
                Block block = stack.pop();
                int cnt = n - block.idx;
                answer = Math.max(answer, cnt * block.height);
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
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
