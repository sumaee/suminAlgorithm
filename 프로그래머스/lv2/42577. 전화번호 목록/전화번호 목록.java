import java.util.*;

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        //전화번호를 담을 set 함수 선언
        Set<String> set = new HashSet<>();
        
        //모든 전화번호를 넣는다
        for(String phone : phone_book){
            set.add(phone);
        }
        
        //배열을 돌면서 특정 부분까지 잘라 접두사가 있는지 확인
        for(int i=0; i<phone_book.length ; i++){
            for(int j=0; j<phone_book[i].length() ; j++){
                if(set.contains(phone_book[i].substring(0, j))){
                    return false;
                }
            }
        }
        
        return true;
    }
}