import java.util.*;

class Solution {
    public String[] solution(String[] records) {
        Map<String, String> map = new HashMap<>();
        List<Info> list = new ArrayList<>();
        for(String record : records){
            String[] strSplit = record.split(" ");
            if(!strSplit[0].equals("Change"))
                list.add(new Info(strSplit[0], strSplit[1]));
            if(strSplit.length >2)
                map.put(strSplit[1], strSplit[2]);
        }
        
        String[] result = new String[list.size()];
        
        for(int i=0; i<result.length; i++){
            result[i] = writeLog(list.get(i), map.get(list.get(i).id));
        }
        
        return result;
        
    }
    public String writeLog(Info info, String nickname){
        String message = "";
        switch(info.state){
            case "Enter": message = "님이 들어왔습니다."; break;
            case "Leave": message = "님이 나갔습니다."; break;
        }
        return nickname+message;
    }
}

class Info{
    String state;
    String id;
    public Info(String state, String id){
        this.state = state;
        this.id = id;
    }
}