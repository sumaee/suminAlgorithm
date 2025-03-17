import java.lang.*;
import java.util.*;

class Solution {
    static int videoLen, curPos, opStart, opEnd;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        videoLen = stringToInt(video_len);
        curPos = stringToInt(pos);
        opStart = stringToInt(op_start);
        opEnd = stringToInt(op_end);
        
        for(String command : commands){
            
            if(curPos>=opStart && curPos <= opEnd){
                curPos = opEnd;
            }
            
            if(command.equals("next")) next();
            else prev();
        }
        
        if(curPos>=opStart && curPos <= opEnd){
            curPos = opEnd;
        }
        
        return intToString(curPos);
    }
    
    private static void next(){
        if(videoLen - curPos < 10){
            curPos = videoLen;
        }else {
            curPos += 10;
        }
    }
    
    private static void prev(){
        if(curPos < 10){
            curPos = 0;
        }else{
            curPos -= 10;
        }
    }
    
    
    private static int stringToInt(String timeStr){
        String[] timeArr = timeStr.split(":");
        
        int minute = Integer.parseInt(timeArr[0]) * 60;
        int second = Integer.parseInt(timeArr[1]);
        
        return minute + second;
    }
    
    private static String intToString(int time){
        StringBuilder sb = new StringBuilder();
        int minute = time / 60;
        int second = time % 60;
        
        if(minute < 10){
            sb.append("0");
        }
        
        sb.append(minute).append(":");
        
        if(second < 10){
            sb.append("0");
        }
        
        sb.append(second);
        
        return sb.toString();
    }
}