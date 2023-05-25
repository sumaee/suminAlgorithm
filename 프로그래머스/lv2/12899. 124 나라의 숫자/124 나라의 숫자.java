class Solution {
    public String solution(int n) {
        String answer = "";
        String[] str = {"4", "1", "2"};
        
        while(n>0){
            int result = n % 3;
            n /= 3;
            
            if(result == 0)  n--;
            
            answer = str[result] + answer;
        }
        
        return answer;
    }
}