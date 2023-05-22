import java.lang.*;
import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> que = new LinkedList<>();
        
        int n = Integer.parseInt(br.readLine());
        for(int i=1; i<=n ; i++){
            que.offer(i);
        }
        
        while(que.size()!=1){
            //첫번째 카드 버리기
            que.poll();
            
            //두번째 카드 뒤로 넣기
            que.offer(que.poll());
        }
        
        System.out.println(que.poll());
        
    }
}