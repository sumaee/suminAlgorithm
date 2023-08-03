import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
          //정해진 조건대로 정렬
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[col - 1] == o2[col - 1]) {
                    return o2[0] - o1[0];
                }
                return o1[col - 1] - o2[col - 1];
            }
        });

        List<Integer> bit = new ArrayList<>();
        for (int i = row_begin; i <= row_end; i++) {
            int result = 0;
            for (int j = 0; j < data[i-1].length; j++) {
                result += data[i - 1][j] % i;
            }

            bit.add(result);
        }

        //XOR 연산 진행
        int answer = bit.get(0);
        for (int i = 1; i < bit.size(); i++) {
            answer ^= bit.get(i);
        }
        return answer;
    }
}