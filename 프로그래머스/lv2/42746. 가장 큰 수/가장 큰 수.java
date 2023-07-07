import java.lang.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
       StringBuilder sb = new StringBuilder();

        int max = Integer.MIN_VALUE;

        List<String> str = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            str.add(String.valueOf(numbers[i]));
            max = Math.max(max, numbers[i]);
        }
        
        
        if (max == 0) {
            return "0";
        }

        str.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        for (int i = 0; i < numbers.length; i++) {
            sb.append(str.get(i));
        }

       return sb.toString();

    }
}
