import java.lang.*;
import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        
        int maxPlayTime = -1;
        String answer = "";

        //m이 #을 가질 수 있으므로 먼저 변환
        m = changeScale(m);

        //각 음악의 정보 담기
        for (String musicInfo : musicinfos) {

            String[] info = musicInfo.split(",");

            //시작 시간과 끝나는 시간을 가지고 플레이 시간 찾기
            int startTime = Integer.parseInt(info[0].substring(0, 2)) * 60 + Integer.parseInt(info[0].substring(3));
            int endTime = Integer.parseInt(info[1].substring(0, 2)) * 60 + Integer.parseInt(info[1].substring(3));
            int playTime = endTime - startTime;

            //일단 곡 정보 #이 붙었으면 변환
            info[3] = changeScale(info[3]);
            //곡 정보가 재생 시간 보다 짧다면 재생 시간보다 길어질때까지 덧붙이기
            if (info[3].length() < playTime) {
                while (info[3].length() < playTime) {
                    info[3] += info[3];
                }
            }

            String scale = info[3].substring(0, playTime);

            //scale을 확인해서 m을 포함하고 있고 재생 시간이 현재 maxPlayTime보다 크다면 answer 갱신
            if (scale.contains(m) && playTime > maxPlayTime) {
                answer = info[2];
                maxPlayTime = playTime;
            }
        }
        
        return maxPlayTime == -1 ? "(None)" : answer;
    }
    
     //#이 붙은 계이름 소문자로 바꾸기
    private String changeScale(String scale) {
        return scale.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a");
    }
}