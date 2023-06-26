import java.lang.*;
import java.util.*;

class Solution {
    public String[] solution(String[] files) {
       
        Arrays.sort(files, (o1, o2) -> {
            //우선 head를 잘라서 소문자로 다 만들어준 후 비교
            String firstHead = o1.split("[0-9]")[0].toLowerCase();
            String secondHead = o2.split("[0-9]")[0].toLowerCase();

            //만약 같은 문자열이라면
            if (firstHead.compareTo(secondHead) == 0) {
                //number만 남겨서 비교
                String firstNumTail = o1.substring(firstHead.length());
                String secondNumTail = o2.substring(secondHead.length());
                return Integer.parseInt(checkNumTail(firstNumTail)) - Integer.parseInt(checkNumTail(secondNumTail));
            } else {
                return firstHead.compareTo(secondHead);
            }
        });

       return files;

    }

    private String checkNumTail(String numTail) {
        StringBuilder sb = new StringBuilder();
        for (char ch : numTail.toCharArray()) {
            // 숫자이고 sb의 길이가 5보다 작다면
            if (Character.isDigit(ch) && sb.length() <= 5) {
                sb.append(ch);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }
}