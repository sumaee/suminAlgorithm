import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int[] firstDp = new int[sticker.length];
        int[] secondDp = new int[sticker.length];
        
        if(sticker.length == 1) return sticker[0];
        
        firstDp[0] = sticker[0];
        firstDp[1] = sticker[0];
        for(int i=2; i<sticker.length-1; i++)
            firstDp[i] = Math.max(firstDp[i-1], firstDp[i-2]+sticker[i]);
        
        secondDp[0] = 0;
        secondDp[1] = sticker[1];
        for(int i=2; i<sticker.length; i++)
            secondDp[i] = Math.max(secondDp[i-1], secondDp[i-2] + sticker[i]);
        
        int answer = Math.max(firstDp[sticker.length-2], secondDp[sticker.length-1]);
        return answer;
    }
}