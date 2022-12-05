import java.lang.*;
import java.util.*;

class Solution {
   int[][] arr;
    int[][] newArr;
    int row, col, c;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        row = triangle.length;
        col = triangle[row-1].length;
        
        newArr = new int[row][col];
        arr = triangle;
        c=col;
        dp(0);
        
        return newArr[0][0];
    }
    
    public void dp(int r){
        if(r==row-1){
            newArr[r] = arr[r];
            return;
        }
        
        if(newArr[r][0]==0){
            
            dp(r+1);
        }
        
        for(int i=0; i<c-1; i++){
            newArr[r][i] = Math.max(newArr[r+1][i],newArr[r+1][i+1])+arr[r][i];
        }

        c--;
    }
}