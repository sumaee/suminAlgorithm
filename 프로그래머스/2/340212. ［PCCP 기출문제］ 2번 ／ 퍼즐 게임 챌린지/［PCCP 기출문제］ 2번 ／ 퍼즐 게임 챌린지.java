import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        long maxLevel = 100000;
        long minLevel = 1;
        long answer = 0;
        
        while(minLevel < maxLevel){
            long mid = (maxLevel + minLevel)/2;
            long solveTime = solvePuzzle(diffs, times, mid);
            
            if(solveTime > limit){
                minLevel = mid+1;
            }else{
                answer = maxLevel;
                maxLevel = mid;
                
            }
        }
        
        return (int) maxLevel;
    }
    private static long solvePuzzle(int[] diffs, int[] times, long mid){
        long solveTime = 0;
        for(int i=0; i<diffs.length ; i++){
            int timeCur = times[i];
            int timePre = i== 0 ? 0 : times[i-1];
            
            if(diffs[i] <= mid){
                solveTime += timeCur;
            }else{
                solveTime += ((diffs[i] - mid) * (timeCur + timePre) + timeCur);
            }
            
        }
        return solveTime;
    }
}