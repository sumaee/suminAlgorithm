import java.lang.*;
import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<WorkInfo> que = new PriorityQueue<>((a, b) -> a.start - b.start);
        
        for(String[] plan : plans){
            String name = plan[0];
            int start = timeToInt(plan[1]);
            int playTime =Integer.parseInt(plan[2]);
            
            que.offer(new WorkInfo(name, start, playTime));
        }
        
        Stack<WorkInfo> stack = new Stack<>();
        String[] answer = new String[plans.length];
        int idx = 0;
        
        while(!que.isEmpty()){
            WorkInfo curr = que.poll();
            int currTime = curr.start;
            
            if(que.isEmpty()){
                answer[idx++] = curr.name;
                
                while(!stack.isEmpty()){
                    answer[idx++] = stack.pop().name;
                }
            }else{
                WorkInfo nxtWork = que.peek();
                
                if(curr.start + curr.playTime > nxtWork.start){
                    stack.push(new WorkInfo(curr.name, curr.playTime - (nxtWork.start - curr.start)));
                }else if(curr.start+curr.playTime == nxtWork.start){
                    answer[idx++] = curr.name;
                }else{
                    answer[idx++] = curr.name;
                    currTime += curr.playTime;
                    
                    while(!stack.isEmpty()){
                        WorkInfo remainWork = stack.pop();
                        
                        if(currTime + remainWork.playTime <= nxtWork.start){
                            currTime+=remainWork.playTime;
                            answer[idx++] = remainWork.name;
                        }else{
                            stack.push(new WorkInfo(remainWork.name, remainWork.playTime - (nxtWork.start - currTime)));
                            break;
                        }
                    }
                }
            }
        }
        return answer;
    }
    
    private static int timeToInt(String time){
        String[] timeArr = time.split(":");
        
        return Integer.parseInt(timeArr[0])*60 + Integer.parseInt(timeArr[1]);
    }
    
    static class WorkInfo{
        String name;
        int start;
        int playTime;
        
        public WorkInfo(String name, int start, int playTime){
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
        
        public WorkInfo(String name, int playTime){
            this.name = name;
            this.playTime = playTime;
        }
    }
}