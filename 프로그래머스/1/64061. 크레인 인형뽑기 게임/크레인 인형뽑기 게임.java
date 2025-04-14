import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Queue<Integer>[] games = new LinkedList[board.length];
        
        for(int i=0; i<games.length ;i++){
            games[i] = new LinkedList<>();
        }
        
        for(int i=0; i<board.length ;i++){
            for(int j=0; j<board[i].length ; j++){
                if(board[i][j] == 0) continue;
                games[j].offer(board[i][j]);
            }
        }
        
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for(int move : moves){
            if(games[move-1].isEmpty()) continue;
            int doll = games[move-1].poll();
            if(!stack.isEmpty() && stack.peek() == doll){
                stack.pop();
                answer+=2;
            }else{
                stack.add(doll);
            }
        }
        return answer;
    }
}