import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int totalPick = picks[0] + picks[1] + picks[2];
        
        // tired[0] : 다이아로 캤을 때 피로도
        // tired[1] : 철로 캤을 때 피로도
        // tired[2] : 돌로 캤을 때 피로도
        int[][] tiredInfo;
        if(minerals.length % 5 == 0){
            tiredInfo = new int[minerals.length/5][3];
        }else{
            tiredInfo = new int[minerals.length/5+1][3];
        }
      
        int idx=0;
        while(idx<minerals.length && totalPick>0){
            String mineral = minerals[idx];
            
            if(mineral.equals("diamond")){
                tiredInfo[idx/5][0] += 1;
                tiredInfo[idx/5][1] += 5;
                tiredInfo[idx/5][2] += 25;
            }else if(mineral.equals("iron")){
                tiredInfo[idx/5][0] += 1;
                tiredInfo[idx/5][1] += 1;
                tiredInfo[idx/5][2] += 5;
            }else{
                tiredInfo[idx/5][0] += 1;
                tiredInfo[idx/5][1] += 1;
                tiredInfo[idx/5][2] += 1;
            }
            
            if(idx%5 == 4) totalPick--;
            idx++;
        }
        
        //돌로 캤을 떄의 피로도를 기준으로 내림차순 정렬
        Arrays.sort(tiredInfo, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] == o2[2]){
                    if(o1[1] == o2[1]){
                        return o2[0] - o1[0];
                    }
                    return o2[1] - o1[1];
                }
                return o2[2] - o1[2];
            }
        });
        
        int answer = 0;
        for(int i=0, pickIdx=0; i<tiredInfo.length; i++){
            while(pickIdx<3 && picks[pickIdx] == 0) pickIdx++;
            
            if(pickIdx == 3) break;
            //사용한 곡괭이 수 줄이기
            picks[pickIdx]--;
            answer += tiredInfo[i][pickIdx];
        }
        
        return answer;
    }
}