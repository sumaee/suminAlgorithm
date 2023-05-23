import java.lang.*;
import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> result1 = new ArrayList<>();
        List<String> result2 = new ArrayList<>();
        List<String> union = new ArrayList<>();
        List<String> intersection = new ArrayList<>();


        //일단 소문자로 만들기
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            char first = str1.charAt(i);
            char second = str1.charAt(i + 1);

            if (first >= 'a' && first <= 'z' && second >= 'a' && second <= 'z') {
                result1.add(first + "" + second);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char first = str2.charAt(i);
            char second = str2.charAt(i + 1);

            if (first >= 'a' && first <= 'z' && second >= 'a' && second <= 'z') {
                result2.add(first + "" + second);
            }
        }

        for (String str : result1) {
            if (result2.remove(str)) {
                intersection.add(str);
            }
            union.add(str);
        }

        union.addAll(result2);
        
        double answer;
        if (union.size() == 0) {
            answer = 1;
        } else {
            answer = ((double) intersection.size() / (double) union.size());
        }
        
        return (int) (answer * 65536);
    }
}