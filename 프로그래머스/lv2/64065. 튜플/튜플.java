import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] arr = new int[100001];
        int[] result = new int[100001];
        
        for(String str : s.replace("{", "").replace("}", "").split(",")){
            int num = Integer.parseInt(str);
            arr[num]++;
        }
        
        int cnt = 0;
        for(int i=0; i<result.length ; i++){
            if(arr[i]!=0){
                result[arr[i]] = i;
                cnt++;
            }
        }
        
        int[] answer = new int[cnt];
        int idx=0;
        for(int i=result.length-1; i>=0 ; i--){
            if(result[i] != 0){
                answer[idx++] = result[i];
            }
        }
        
        return answer;
    }
}