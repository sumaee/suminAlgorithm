import java.lang.*;
import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
         //시작 시간 순으로 정렬
        PriorityQueue<Work> works = new PriorityQueue<Work>((o1, o2) -> o1.start - o2.start);

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];

            //시간 정리해서 넣기
            int start = Integer.parseInt(plans[i][1].split(":")[0]) * 60 + Integer.parseInt(plans[i][1].split(":")[1]);
            int time = Integer.parseInt(plans[i][2]);

            works.offer(new Work(name, start, time));
        }

        //최근에 멈춘 과제부터 진행해야하므로 스택으로 멈춘 과제 저장
        Stack<Work> stack = new Stack<>();
        String[] answer = new String[plans.length];
        int idx = 0;
        while (!works.isEmpty()) {
            Work curr = works.poll();

            int nowTime = curr.start;

            //이후에 해야할 과제가 있다면 다음 과제 확인
            if (!works.isEmpty()) {
                Work nxt = works.peek();
                //지금 해야하는 거 멈추고 새로운 과제를 해야한다면
                if (curr.start + curr.time > nxt.start) {
                    //지금 과제를 스택에 넣어두고 다음 과제 시작 (남은 시간을 저장해둠)
                    int remainTime = curr.time - (nxt.start - curr.start);
                    stack.push(new Work(curr.name, curr.start, remainTime));
                }

                //지금 과제 끝내고 바로 시작할 수 있다면 지금 과제 끝냈으므로 정답 배열에 저장
                else if (curr.start + curr.time == nxt.start) {
                    answer[idx++] = curr.name;
                }

                //지금 과제를 끝내고 다음 과제를 할 수 있는 시간이 남아있다면
                else {
                    //일단 끝난 과제 정답에 넣기
                    answer[idx++] = curr.name;
                    //해당 과제 끝난 시간으로 지금 시간 바꾸기
                    nowTime += curr.time;


                    //남은 과제 진행
                    while (!stack.isEmpty()) {
                        Work remainWork = stack.pop();

                        //다음 과제 전까지 끝낼 수 있다면
                        if (nowTime + remainWork.time <= nxt.start) {
                            nowTime += remainWork.time;
                            answer[idx++] = remainWork.name;
                        }
                        //다음 과제 전까지 못끝낸다면
                        else {
                            int remainTime = remainWork.time - (nxt.start - nowTime);
                            stack.push(new Work(remainWork.name, remainWork.start, remainTime));
                            break;
                        }
                    }
                }
            }

            //다음 진행해야할 과제가 없다면
            else {
                //현재 과제부터 해결하고
                answer[idx++] = curr.name;
                //스택에 저장된 과제가 남아있다면 진행하기
                if (!stack.isEmpty()) {
                    //스택에 남아있는 과제 해결
                    while (!stack.isEmpty()) {
                        answer[idx++] = stack.pop().name;
                    }
                }
            }
        }

        return answer;
    }
}

class Work {
    String name;
    int start;
    int time;

    public Work(String name, int start, int time) {
        this.name = name;
        this.start = start;
        this.time = time;
    }
}