import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(int sco : scoville)
            que.offer(sco);
        
        int answer = 0;
        while(true){
            //우선 가장 맵지 않은 스코빌 지수 꺼내기
            int first = que.poll();
            
            //꺼낸 스코빌이 K 이상이면 다른 값들도 K 이상이므로 끝내기
            if(first >= K)
                return answer;
            
            //첫번째를 꺼내고 큐가 비었다면 모든 스코빌의 값이 K를 못넘기는 것이므로 끝
            if(que.isEmpty())
                break;
            
            //두번째로 맵지 않은 스코빌 지수 꺼내기
            int second = que.poll();
            
            //섞은 음식의 스코빌 지수
            int newThing = first + second*2;
            
            //섞은 횟수 한번 추가
            answer ++;
            
            //섞은 값 que에 넣기
            que.offer(newThing);
        }
        
        return -1;
    }
}