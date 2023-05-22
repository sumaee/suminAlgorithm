import java.lang.*;
import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Num> stack = new Stack<>();
        for(int i=0; i<numbers.length ; i++){
            int num = numbers[i];
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
        return answer;
    }
    
}

class Num{
    int idx, num;

    public Num(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }
}