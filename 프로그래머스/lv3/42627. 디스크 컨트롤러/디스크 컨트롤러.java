import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        //우선 요청 순서에 따라 오름차순으로 정렬
        Arrays.sort(jobs, (job1, job2)->job1[0]-job2[0]);
        
        //하나의 일을 처리하고 있는 동안 요청 시간이 된 것들을 넣을 큐 선언
        //소요시간이 짧은 애를 먼저하는 것이 유리하므로 짧은 것을 먼저 뽑아냄
        PriorityQueue<int[]> que = new PriorityQueue<>((job1, job2)-> job1[1]-job2[1]);
        
        int time = 0; // 현재 시간
        int idx = 0; // jobs의 인덱스
        int cnt = 0; // 처리한 jobs의 개수
        
        //cnt(처리한 jobs의 개수)가 들어온 jobs의 개수보다 작을 때까지 진행
        while(cnt<jobs.length){
            //해당 시간에 요청이 들어온 작업을 넣어줌
            while(idx<jobs.length && jobs[idx][0]<=time){
                que.offer(jobs[idx++]);
            }
            
            //큐가 비어있다면 다음 작업 시작을 위해 다음작업의 요청 시간으로 time을 맞춤
            if(que.isEmpty()) time = jobs[idx][0];
                
            //큐가 비어있지 않다면 큐 안에 있는 것들 중 소요 시간이 짧은 순으로 작업 처리
            else{
                //소요 시간이 짧은 작업이 먼저 나오게됨
                int[] curr = que.poll();
                
                //현재 작업이 끝날 때까지 소요되는 총 시간은
                //현재 작업의 소요 시간 + 작업이 시작된 시간 - 작업 요청이 들어온 시간
                answer += curr[1] + time - curr[0];
                
                //시간의 흐름은 현재 작업이 끝나는 시간이므로 소요 시간을 더해줌
                time +=curr[1];
                
                //작업 하나를 처리했다는 표시
                cnt++;
            }
        }
        
        answer = (int)(answer/jobs.length);
        return answer;
    }
   
}