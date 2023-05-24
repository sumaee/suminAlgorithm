import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
          Deque<Num> que = new ArrayDeque<>();
        //정답을 담을 값
        Deque<Num> result = new ArrayDeque<>();
        int[] answer = new int[2];
        int sum = 0;
        for (int i = 0; i < sequence.length; i++) {
            sum += sequence[i];
            que.offer(new Num(i, sequence[i]));

            //sum 이 k보다 커지면 작아질때까지 앞에 값 빼기
            if (sum > k) {
                while (sum > k) {
                    sum -= que.pollFirst().num;
                }
            }

            //sum 이 k 가 되고 result 에 값이 없다면 que 그대로 넣기
            if (sum == k && result.size() == 0) {
                result.addAll(que);
            } 
            //sum이 k 가 되고 que의 사이즈가 result 보다 작다면 갱신
            else if (sum == k && que.size() < result.size()) {
                result.clear();
                result.addAll(que);
            }


        }

        //배열을 다 돌고 나온 answer의 인덱스 값만 뽑아내기
        //만약 answer의 size가 1이라면 하나로 되어있는 것이므로 같은 인덱스 두번 뽑기
        if (result.size() == 1) {
            Num num = result.poll();
            answer[0] = num.idx;
            answer[1] = num.idx;
        } else {
            answer[0] = result.peekFirst().idx;
            answer[1] = result.peekLast().idx;
        }

        return answer;

    }
}

class Num {
    int idx, num;

    public Num(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }
}
