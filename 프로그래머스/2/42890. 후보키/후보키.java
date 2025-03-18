import java.lang.*;
import java.util.*;

class Solution {
    static List<String> candiList;
    static boolean[] visited;
    public int solution(String[][] relation) {
        candiList = new ArrayList<>();
        
        for(int i=0 ;i<relation[0].length ; i++){
            visited = new boolean[relation[0].length];
            comb(0, 0, i+1, relation);
        }
        int answer = candiList.size();
        return answer;
    }
    
    private static void comb(int depth, int curr, int size, String[][] relation){
        if(depth == size){
            List<Integer> list = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<visited.length ; i++){
                if(visited[i]){
                    sb.append(i);
                    list.add(i);
                }
            }
        
            //유일성 검사
            if(!unique(relation, list)) return;
            
            //최소성 검사
            String key = sb.toString();
            for(String str : candiList){
                int cnt = 0;
                for(int i=0; i<key.length() ; i++){
                    String s = String.valueOf(key.charAt(i));
                    if(str.contains(s)) cnt++;
                }
                
                if(cnt == str.length()) return;
            }
            
            candiList.add(key);
            return;
        }
        
        for(int i=curr ; i<visited.length ;i++){
            if(visited[i]) continue;
            visited[i] = true;
            comb(depth+1, i, size, relation);
            visited[i] = false;
        }
    }
    
    private static boolean unique(String[][] relation, List<Integer> key){
        List<String> list = new ArrayList<>();
        for(int i=0; i<relation.length ;i++){
            StringBuilder sb = new StringBuilder();
            for(int j : key){
                sb.append(relation[i][j]);
            }
            
            if(list.contains(sb.toString())){
                return false;
            }
            
            list.add(sb.toString());
        }
        
        return true;
    }
}