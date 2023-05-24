import java.lang.*;
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();

        //m명이 진행하면서 t만큼 말하기 위해서는 n진수로 바꾼 수의 길이가 m*t만큼 만들어져야함
        for (int i = 0; sb.length() <= m * t; i++) {
            sb.append(Integer.toString(i, n));
        }

        //해당 위치의 수만 말하기
        for (int i = p-1; result.length() != t; i += m) {
            result.append(sb.charAt(i));

        }
        String answer = result.toString().toUpperCase();
        return answer;

    }
}
