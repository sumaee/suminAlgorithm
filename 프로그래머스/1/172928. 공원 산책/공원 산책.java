import java.lang.*;
import java.util.*;
class Solution {
    public int[] solution(String[] park, String[] routes) {
        int row = -1;
        int col = -1;
        
        out : for(int i=0; i<park.length ;i++){
            for(int j=0; j<park[i].length(); j++){
                if(park[i].charAt(j) == 'S'){
                    row = i;
                    col = j;
                    break out;
                }
            }
        }
        
        for(String route : routes){
            String[] routeTmp = route.split(" ");
            String dir = routeTmp[0];
            int moveCnt = Integer.parseInt(routeTmp[1]);
            
            int drow = row;
            int dcol = col;
            
            boolean flag = true;
            if(dir.equals("E")){
                while(moveCnt-->0){
                    dcol++;
                    
                    if(dcol>=park[0].length() || park[drow].charAt(dcol) == 'X'){
                        flag = false;
                        break;
                    }
                }
            }else if(dir.equals("W")){
                while(moveCnt-->0){
                    dcol--;
                    
                    if(dcol<0 || park[drow].charAt(dcol) == 'X'){
                        flag = false;
                        break;
                    }
                }
            }else if(dir.equals("S")){
                while(moveCnt-->0){
                    drow++;
                    
                    if(drow>=park.length || park[drow].charAt(dcol) == 'X'){
                        flag = false;
                        break;
                    }
                }
            }else{
                while(moveCnt-->0){
                    drow--;
                    
                    if(drow<0 || park[drow].charAt(dcol) == 'X'){
                        flag = false;
                        break;
                    }
                }
            }
            
            if(flag){
                row = drow;
                col = dcol;
            }
        }
        return new int[]{row, col};
    }
}