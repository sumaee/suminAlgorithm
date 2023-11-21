import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        Stack<Info> stack = new Stack<>();
        stack.push(new Info(heights[0], 1));
        long answer = 0;
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().height < heights[i]) {
                Info curr = stack.pop();
                answer += curr.cnt;
            }

            //만약 스택이 비어있으면 그냥 넣기
            if (stack.isEmpty()) {
                stack.push(new Info(heights[i], 1));
            }
            //스택이 비어있지 않다면
            else {
                //stack의 마지막 사람의 키가 현재 사람의 키보다 크다면 정답 하나 증가시키고 넣기
                if (stack.peek().height > heights[i]) {
                    stack.push(new Info(heights[i], 1));
                    answer++;
                }
                //stack의 마지막 사람의 키가 현재 사람의 키보다 작거나 같다면 그 사람과 키가 같은 사람의 개수를 정답에 추가
                else {
                    answer += stack.peek().cnt;
                    stack.peek().cnt++;
                    //만약 stack안에 사람이 2명이상있다면 제일 큰사람과도 연결
                    if (stack.size() >= 2) {
                        answer++;
                    }
                }
            }

        }
        System.out.println(answer);

    }

    static class Info {
        int height, cnt; // cnt : 키가 같은 사람들을 기록하기 위함

        public Info(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
}