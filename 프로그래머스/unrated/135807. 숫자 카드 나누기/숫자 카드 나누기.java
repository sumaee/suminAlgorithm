import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        
        //각 배열의 최대공약수 구하기
        for(int i=1 ;i<arrayA.length ; i++){
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }
        
        //arrayA의 최대공약수가 arrayB에서 나눠지면 안됨
        if(check(arrayB, gcdA)){
            answer = gcdA;
        }
        
        //반대로 arrayB 최대공약수가 arrayA에서 나눠지면 안됨
        if(check(arrayA, gcdB)){
            //위에서 나온 answer 과 비교해서 큰 값을 반환
            answer = Math.max(answer, gcdB);
        }
        return answer;
    }
    
   //최대공약수 구하기 
    private int gcd(int a, int b){
        while(b>0){
            int r = a%b;
            a = b;
            b = r;
        }
        
        return a; //a,b의 최대공약수
    }
    
    // 조건 만족하는지 확인
    private boolean check(int[] arr , int gcd){
        for(int num : arr){
            //만약 나누어 떨어진다면 false 반환
            if(num % gcd == 0){
                return false;
            }
        }
        
        //여기까지 왔다는건 나누어 떨어지지 않는다는 것이므로 true 반환
        return true;
    }
}