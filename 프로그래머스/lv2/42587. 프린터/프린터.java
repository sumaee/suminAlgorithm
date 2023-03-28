import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
       Queue<Print> que = new LinkedList<>();
        
        //location을 기록하기 위해 que에 담기
        for(int i=0; i<priorities.length ; i++){
            boolean check = false;
            if(i==location){
                check = true;
            }
            que.offer(new Print(priorities[i], check));
        }
        
        
        int answer = 0;
        while (!que.isEmpty()) {
            //현재 프린트 할 것을 뽑기
            Print print = que.poll();
            boolean check = false;
            
            //큐를 돌며 지금 프린트할 것보다 중요한게 있는지 확인
            for(Print p : que){
                //더 중요한게 있다면 check를 true로 만들고 for문 끝내기
                if(print.priority<p.priority){
                    check = true;
                    break;
                }
            }
            
            //현재 프린트할 것보다 더 중요한게 있다면 현재 프린트를 다시 que에 넣기
            if(check){
                que.offer(print);
                continue;
            }
            
            //현재꺼를 프린트 한다면 답더하기
            answer++;
            
            //현재 프린트 할 것이 요청한 인쇄 목록이라면 답 내고 끝내기
            if(print.locate){
                break;
            }
        }
        
        return answer;
    }
}

class Print{
    int priority;
    boolean locate;

    public Print(int priority, boolean locate){
        this.priority = priority;
        this.locate = locate;
    }
}