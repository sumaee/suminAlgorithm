import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        Queue<ServerInfo> que = new LinkedList<>();
        int answer = 0;
        int currServerCnt = 0;
        for(int i=0; i<players.length; i++){
            // 큐에 있는 서버 종료 시간이 넘었다면 종료
            if(!que.isEmpty() && que.peek().startTime<= i-k){
                currServerCnt -= que.poll().cnt;
            }
            //현재 필요한 서버
            int needServerCnt = players[i]/m;
            //서버 증설이 필요하다면 증설
            if(currServerCnt < needServerCnt){
                answer += (needServerCnt - currServerCnt);
                que.offer(new ServerInfo(i, needServerCnt - currServerCnt));
                currServerCnt += (needServerCnt - currServerCnt);
            }
        }
        
        return answer;
    }
    
    static class ServerInfo{
        int startTime, cnt;
        
        public ServerInfo(int startTime, int cnt){
            this.startTime = startTime;
            this.cnt = cnt;
        }
    }
}