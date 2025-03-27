import java.lang.*;
import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (o1, o2) -> {
            if(o1.length() == o2.length()){
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        
        Queue<String> banQue = new LinkedList<>();
        for(String ban : bans){
            banQue.offer(ban);
        }
        
        while(!banQue.isEmpty()){
            String curr = banQue.peek();
            String str = makeStr(n);
            
            if(curr.length() < str.length() || (curr.length() == str.length() && curr.compareTo(str) <= 0 )){
                n++;
                banQue.poll();
                continue;
            }
            
            break;
            
        }
        
        return makeStr(n);
    }
    
    private static String makeStr(long n){
        StringBuilder sb = new StringBuilder();
        while(n>0){
            long remind = n % 26;
            n/=26;
            if(remind == 0){
                n--;
                sb.append('z');
            }else {
                sb.append((char)('a' + remind -1));
            }
        }
        return sb.reverse().toString();
    }
    
}