import java.util.*;
import java.lang.*;

class Solution {
    static int[] fiboarr;
    public int solution(int n) {
        fiboarr = new int[n+1];
        fiboarr[0]=0;
        fiboarr[1]=1;
        
        
        int answer = fibo(n)%1234567;
        
        return answer;
    }
    
    private static int fibo(int a){
        
        for(int i=2; i<=a; i++){
            fiboarr[i]=(fiboarr[i-1]+fiboarr[i-2])%1234567;
        }
        return fiboarr[a];
    }
}