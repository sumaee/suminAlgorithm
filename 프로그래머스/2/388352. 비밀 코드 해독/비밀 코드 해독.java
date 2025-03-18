import java.lang.*;
import java.util.*;

class Solution {
    static int[] numArr;
    static boolean[] visited;
    static int answer, r, c;
    static List<Integer>[] list;
    public int solution(int n, int[][] q, int[] ans) {
        list = new List[q.length];
        r = q.length;
        c = q[0].length;
        for(int i=0; i<r ;i++){
            list[i] = new ArrayList<>();
            for(int j=0; j<c ;j++){
                list[i].add(q[i][j]);
            }
        }
        
        visited = new boolean[n+1];
        numArr = new int[5];
        chooseNum(0, 1, n, ans);
        return answer;
    }
    
    private static void chooseNum(int cnt, int curr, int n, int[] ans){
        if(cnt == 5){
            if(checkAnswer(ans)){
                answer++;
            }
            return;
        }
        
        for(int i=curr ; i<=n ; i++){
            if(visited[i]) continue;
            visited[i] = true;
            numArr[cnt] = i;
            chooseNum(cnt+1, i+1, n, ans);
            visited[i] = false;
        }
    }
    
    private static boolean checkAnswer(int[] ans){
        for(int i=0; i<r ; i++){
            int cnt = 0;
            for(int num : numArr){
                if(list[i].contains(num)) cnt++;
            }
            
            if(cnt != ans[i]) return false;
        }
        return true;
    }
}