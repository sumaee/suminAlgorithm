import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        Arrays.sort(arr);
        //원소가 1개인 경우
        if(arr.length ==1) return arr[0];
        
        //원소가 2개인 경우
        int gValue = gcd(arr[1], arr[0]);
        answer = (arr[0]*arr[1])/gValue;
        
        //원소가 3개 이상인 경우
        if(arr.length >=3){
            for(int i=2; i<arr.length; i++){
                gValue = gcd(answer, arr[i]);
                answer = (arr[i]*answer)/gValue;
            }
        }
        return answer;
    }
    
    private int gcd(int max, int min){
        int value = max % min;
        if(value == 0) return min;
        return gcd(min, value);
    }
}