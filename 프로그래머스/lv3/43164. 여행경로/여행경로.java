import java.lang.*;
import java.util.*;

class Solution {
    String[] answer;
    boolean[] visited;
 
    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length+1];
        //티켓 사용 유무를 확인하기 위해
        visited = new boolean[tickets.length]; 
        
       //도착지점을 정렬
        Arrays.sort(tickets, (ticket1, ticket2)-> ticket1[1].compareTo(ticket2[1]));
        
        answer[0] = "ICN";
        findRoute(tickets, "ICN", 0);
        return answer;
    }
    
    private boolean findRoute(String[][] tickets, String st, int idx){
        if(answer[tickets.length]!=null){
            return true;
        }
        
        for(int i=0; i<tickets.length; i++){
            // 만약 출발지가 st와 똑같고 방문하지 않은 곳이라면 방문
            if(!visited[i] && tickets[i][0].equals(st)){
                visited[i]=true;
                answer[idx+1]=tickets[i][1];
                if(findRoute(tickets, tickets[i][1], idx+1)) return true;
                visited[i]=false;
            }
        }
        
        return false;
    }
}