import java.lang.*;
import java.util.*;

/*
A가 주사위를 선택해서 나올 수 있는 모든 주사위의 합 배열 만들고
B도 마찬가지로 진행

합 배열로만 비교
*/
class Solution {
    static int[] answer;
    static int max = 0;
    static int[] chooseDice;
    static boolean[] visited;
    static List<Integer> arrA, arrB;
    public int[] solution(int[][] dice) {
        answer = new int[dice.length/2];
        chooseDice = new int[dice.length/2];
        visited = new boolean[dice.length];
        
        dfs(0, dice.length, 0, dice);
        return answer;
    }
    
    private static void dfs(int depth, int n, int cur, int[][] dice){
        if(depth == n/2){
            int winCnt = calWinCnt(n, dice);
            if(winCnt > max){
                max = winCnt;
                
                int idx = 0;
                for(int i=0; i<visited.length ;i++){
                    if(visited[i]){
                        answer[idx++] = i+1;
                    }
                }
            }
            return;
        }
        
        for(int i=cur ; i<n ; i++){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(depth+1, n, i+1, dice);
            visited[i] = false;
        }
    }
    
    private static int calWinCnt(int n, int[][] dice){
        int winCnt = 0;
        
        //A, B 배열만들기
        makeDiceArr(n, dice);
        
        Collections.sort(arrB);
        
        for(int aNum : arrA){
            int left = 0;
            int right = arrB.size()-1;
            
            int idxCnt = -1;
            while(left<=right){
                int mid = (left + right) /2;
                
                if(arrB.get(mid) < aNum){
                    left = mid+1;
                    idxCnt = Math.max(idxCnt, mid);
                }else{
                    right = mid-1;
                }
            }
            
            if(idxCnt != -1){
                winCnt += (idxCnt+1);
            }
        }
        
        return winCnt;
    }
    
    private static void makeDiceArr(int n, int[][] dice){
        arrA = new ArrayList<>();
        arrB = new ArrayList<>();
        
        int[][] diceA = new int[n/2][6];
        int[][] diceB = new int[n/2][6];
        
        int aIdx=0;
        int bIdx = 0;
        for(int i=0; i<n ;i++){
            if(visited[i]){
                diceA[aIdx++] = dice[i];
            }else{
                diceB[bIdx++] = dice[i];
            }
        }
        
        makeSumArr(arrA, diceA, 0, 0, n);
        makeSumArr(arrB, diceB, 0, 0, n);
    }
    
    private static void makeSumArr(List<Integer> arr, int[][] dice, int depth, int sum, int n){
        if(depth == n/2){
            arr.add(sum);
            return;    
        }
        
        for(int i=0 ;i<6 ; i++){
            int sumTmp = sum + dice[depth][i];
            makeSumArr(arr, dice, depth+1, sumTmp, n);
        }
        
    }
}