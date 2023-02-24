import java.lang.*;
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        List<Integer> list = new ArrayList<>();

        for(String time : timetable){
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(3, 5));
            
            list.add(hour * 60+min);
        }
        //시간 순으로 정렬
        list.sort((o1, o2)-> o1-o2);
        
        //버스 운행 정보
        Bus[] buses = new Bus[n];        
        for(int i=0; i<n ; i++)
            buses[i] = new Bus(540+t*i);
        
        int idx=0;
        //줄선 사람 수만큼 실행
        for(int i=0; i<list.size() ; i++){
            
            while(idx < buses.length){
                //버스 태우기
                if(buses[idx].start>=list.get(i) && buses[idx].cnt != m){
                    buses[idx].ride(list.get(i));
                    break;
                }
                
                idx++;
                
            }
        }
        
        // 탈 수 있는 마지막 버스에 사람이 꽉차있다면 마지막으로 탄 사람보다 1분 빠르게나오기
        int answer;
        if(buses[n-1].cnt==m)
            answer = buses[n-1].lastTime -1;
        else
            answer = buses[n-1].start;
        
        return toString(answer);
    }
    
    public String toString(int time){
        int hour = time / 60;
        int min = time % 60;
        
        String h = "";
        String m = "";
        if(hour < 10)
            h += "0";
        
        if(min < 10)
            m+="0";
        
        String result = h + hour + ":" + m + min;
            
        return result;
    }
}

class Bus {
    int start; //처음 버스 셔틀 시작 시간
    int lastTime;// 버스에 마지막으로 타는 사람 시간
    int cnt;// 버스에 탄 사람 수
    
    public Bus(int start){
        this.start = start;
    }
    
    public void ride(int time){
        cnt++;
        lastTime = time;
    }
    
}