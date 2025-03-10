import java.lang.*;
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, GiftInfo> map = new HashMap<>();
        
        for(int i=0; i<friends.length ; i++){
            map.put(friends[i], new GiftInfo(i));
        }
        
        int[][] info = new int[friends.length][friends.length];
        for(String gift : gifts){
            String givePerson = gift.split(" ")[0];
            String receivePerson = gift.split(" ")[1];
            
            //선물 준사람 선물 지수 수정
            GiftInfo giftInfo = map.get(givePerson);
            giftInfo.giveGiftCnt++;
            
            //선물 받은 사람 선물지수 수정
            giftInfo = map.get(receivePerson);
            giftInfo.receiveGiftCnt++;
            
            //선물 주고 받은 정보 입력
            info[map.get(givePerson).idx][map.get(receivePerson).idx]++;
        }
        
        int[] nxtGiftCnt = new int[friends.length];
        for(int i=0; i<friends.length ; i++){
            GiftInfo a = map.get(friends[i]);
            
            for(int j=i+1 ; j<friends.length ; j++){
                GiftInfo b = map.get(friends[j]);
                
                //주고 받은 기록이 없거나 주고 받은 수가 같다면 선물지수 비교
                if((info[a.idx][b.idx] == 0 && info[b.idx][a.idx] == 0) 
                   || info[a.idx][b.idx] == info[b.idx][a.idx]){
                    int aGiftScore = a.giveGiftCnt - a.receiveGiftCnt;
                    int bGiftScore = b.giveGiftCnt - b.receiveGiftCnt;
                    
                    if(aGiftScore > bGiftScore) nxtGiftCnt[a.idx]++;
                    else if(aGiftScore < bGiftScore) nxtGiftCnt[b.idx]++;
                }else if(info[a.idx][b.idx] > info[b.idx][a.idx]){
                    nxtGiftCnt[a.idx]++;
                }else{
                    nxtGiftCnt[b.idx]++;
                }
            }
        }
        
        int max = 0;
        for(int i=0; i<friends.length ;i++){
            max = Math.max(max, nxtGiftCnt[i]);
        }
        
        return max;
    }
    
    static class GiftInfo{
        int idx, giveGiftCnt, receiveGiftCnt;
        
        public GiftInfo(int idx){
            this.idx = idx;
            this.giveGiftCnt = 0;
            this.receiveGiftCnt = 0;
        }
    }
}