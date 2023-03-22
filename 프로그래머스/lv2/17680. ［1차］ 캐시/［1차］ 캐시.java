import java.util.*;

class Solution{
    Queue<String> que;
    int answer;
    public int solution(int cacheSize, String[] cities){
        que = new LinkedList<>();
        answer = 0;
        
        for(String str : cities){
          //우선 들어오는 도시 소문자로 만들기
          String city = str.toLowerCase();
          //만약 넣으려던 도시가 que안에 있다면 제거하고 맨 뒤에 넣어주기
          if(que.contains(city)){
              removeAndAdd(city);
          }
          //만약 넣으려던 도시가 없다면
          else{
             addCity(city, cacheSize);
          }
      }
      
      return answer;
      
  }
    
    //큐안에 도시가 없을 때
    public void addCity(String city, int cacheSize){
        
        //큐 사이즈가 0보다는 크고 캐시 사이즈 보다 작다면 그냥 추가하기
        if(cacheSize>0){
            if(que.size() < cacheSize){
                que.offer(city);
            }
        
            //크다면 맨 앞에를 삭제하고 넣기
            else{
                que.poll();
                que.offer(city);
            }
        }
        answer+=5;
    }
    
    //큐안에 도시가 있을 때
    public void removeAndAdd(String city){
        que.remove(city);
        que.offer(city);
        answer+=1;
    }
}