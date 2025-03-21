import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for(String term : terms){
            String[] termArr = term.split(" ");
            termMap.put(termArr[0], Integer.parseInt(termArr[1]));
        }
        
        List<Integer> answerTmp = new ArrayList<>();
        int idx = 1;
        int todayInt = dateToInt(today);

        for(String privacy : privacies){
            String[] privacyArr = privacy.split(" ");
            int dateInt = dateToInt(privacyArr[0]);
            int endDate =( dateInt + termMap.get(privacyArr[1])*28);
            
            if(todayInt>=endDate) answerTmp.add(idx);
            idx++;
        }
        
        return answerTmp.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private static int dateToInt(String date){
        String[] dateArr = date.split("\\.");
        
        int year = Integer.parseInt(dateArr[0]) * 12 * 28;
        int month = Integer.parseInt(dateArr[1]) * 28;
        int day = Integer.parseInt(dateArr[2]);
        
        return year + month + day;
    }
}