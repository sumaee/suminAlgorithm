import java.lang.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        //시간 오름차순으로 정리
        Arrays.sort(times);
        
        //심사 시간 범위
        long left = 0;
        long right =(long) n * times[times.length-1];
        
        while(left<=right){
            long mid = (left+right)/2;
            long people = 0;
            
            //심사관마다 mid 시간기준으로 심사할 수 있는 사람수를 구함
            for(int time : times){
                people += mid / time;
            }
            
            //만약 심사할 수 있는 사람수가 주어진 사람보다 많거나 같으면 최소 시간이 존재하므로 right값 변경
            if(people >= n){
                right = mid-1;
                answer = mid;
            }
            //심사할 수있는 사람이 주어진 사람보다 작다면 시간을 늘려야하므로 left 값 변경
            else{
                left = mid + 1;
            }
        }
        return answer;
    }
}