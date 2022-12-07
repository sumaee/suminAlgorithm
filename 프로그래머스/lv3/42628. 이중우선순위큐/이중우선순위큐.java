import java.util.*;
import java.lang.*;

class Solution {
    public int[] solution(String[] operations) {
        // 숫자를 담을 큐
        PriorityQueue<Integer> que = new PriorityQueue<>();
        //삭제를 하고 나서 que에 담기 위해 새롭게 담을 que
        PriorityQueue<Integer> newQue = new PriorityQueue<>();
        
        for(String str : operations){
            String[] arr = str.split(" ");
            String op = arr[0];
            
            switch(op){
                //I라면 집어넣기 -> String이므로 parseint해서 넣어주기
                case "I":
                    que.add(Integer.parseInt(arr[1]));
                    break;
                
                //D라면 삭제하는데 -1일때와 1일때 구분해서 다르게 하기
                case "D":
                    if(arr[1].equals("-1")){
                        que.poll();
                    }else{
                        //1이라면 최댓값을 넣는 것이므로 우선순위 큐에서 하나씩 빼다가 마지막꺼를 안넣기
                        int queSize = que.size();
                        
                        for(int i=0; i<queSize-1; i++){
                            newQue.offer(que.poll());
                        }
                        
                        //newQue를 que에 다시 옮기기
                        que = newQue;
                    }
                    break;
            }   
        }
        
        int[] answer = new int[2];
        int queSize = que.size();
        
        if(queSize==0){
            answer[1] = 0;
            answer[0] = 0;  
        }else{
            //최솟값 일단 넣고
            answer[1] = que.poll();
            
            //가운데 있는 값 다 버리고 마지막 최댓값만 반환
            while(que.size()!=1){
                que.poll();
            }
            answer[0] = que.poll();
        }
        
        return answer;
    }
}