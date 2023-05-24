import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Long> que1 = new LinkedList<>();
        Queue<Long> que2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for(int num : queue1){
            que1.offer((long)num);
            sum1+=num;
        }
        for(int num : queue2){
            que2.offer((long)num);
            sum2+=num;
        }
        long sum = sum1+sum2;
        if(sum%2==1) return -1;
        
        sum /= 2;
        
        int q1 = 0;
        int q2 = 0;
        //최대 큐의 길이의 2배만큼밖에 못움직임-> 그 이상으로는 반복
        while(q1 <= queue1.length*2 && q2 <= queue2.length*2){
            //합이 같다면 끝
            if(sum1 == sum2) return q1+q2;
            
            //합이 큰 곳에서 빼서 작은 곳으로 집어넣기
            if(sum1>sum){
                sum1 -= que1.peek();
                sum2 += que1.peek();
                que2.offer(que1.poll());
                q1++;
            }else{
                sum2 -= que2.peek();
                sum1 += que2.peek();
                que1.offer(que2.poll());
                q2++;
            }
        }
        
        //위에 같다면 끝에 안걸리면 답이 없는 것이므로 -1
        return -1;
    }
}