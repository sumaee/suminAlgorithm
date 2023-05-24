import java.lang.*;
import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            //짝수일 때는 그냥 하나 큰수가 정답
            if (numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
            }
            //홀수일 때는 2진수로 바꿔서 제일 먼저 나오는 0과 1의 자리 바꿔주기
            // ex ) 3 -> 0011 / 0101 -> 5
            else {
                String binary = Long.toString(numbers[i], 2);
                int firstZero = binary.lastIndexOf("0");
                //0이 중간에 있을 경우
                if (firstZero != -1) {
                    binary = binary.substring(0, firstZero) + "10" + binary.substring(firstZero + 2);
                    answer[i] = Long.parseLong(binary, 2);
                }
                //0 없이 1로만 이루어져 있을 경우
                else {
                    binary = "10" + binary.substring(1);
                    answer[i] = Long.parseLong(binary, 2);
                }
            }
        }

        return answer;
    }
}
