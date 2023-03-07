import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] a) {
        //하나의 숫자를 기준으로 왼쪽과 오른쪽에 작은 것이 있으면 그 기준 숫자는 남을 수 없음
        
        //왼쪽수들의 최솟값 저장 배열
        int[] leftMin = new int[a.length];
        //오른쪽수들의 최솟값 저장 배열
        int[] rightMin = new int[a.length];
        
        int left = a[0];
        int right = a[a.length-1];
        
        //왼쪽 최솟값 배열 찾기
        for(int i=1; i<a.length; i++){
            if(left>a[i]) left = a[i];
            leftMin[i] = left;
        }
        
        //오른쪽 최솟값 배열 찾기
        for(int i=a.length-2; i>=0; i--){
            if(right > a[i]) right = a[i];
            rightMin[i] = right;
        }
        
        //풍선이 1개라면 답은 1개
        if(a.length==1) return 1;
        //배열 같이 돌면서 기준점이 rightMin과 leftMin보다 크다면 그 수는 안되는 거임 
        //맨 왼쪽, 맨 오른쪽의 수는 무조건 남을 수밖에 없으므로 answer = 2부터 시작
        int answer=2;
        for(int i=1; i<a.length-1; i++){
            if(a[i]>leftMin[i] && a[i] > rightMin[i]) continue;
            answer++;
        }
        return answer;
    }
}