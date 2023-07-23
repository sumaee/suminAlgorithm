import java.lang.*;
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        int len = number.length() - k;

        for (int start = 0; start < number.length() && sb.length() != len; ) {
            int limit = k + sb.length() + 1;
            int num = 0;
            for (int j = start; j < limit; j++) {
                if (num < number.charAt(j) - '0') {
                    num = number.charAt(j) - '0';
                    start = j + 1;
                }
            }

            sb.append(num);
        }
        return sb.toString();
    }
}