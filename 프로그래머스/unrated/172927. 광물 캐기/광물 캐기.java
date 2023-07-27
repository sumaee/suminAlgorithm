import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {

        //가지고 있는 곡괭이의 수
        int limit = picks[0] + picks[1] + picks[2];

        //mineral을 5개씩 잘라 저장하고 각 곡괭이로 캤을 때의 피로도를 저장할 배열
        int[][] info;
        if (minerals.length % 5 == 0) {
            info = new int[minerals.length / 5][3];
        } else {
            info = new int[minerals.length / 5 + 1][3];
        }

        //곡괭이가 존재하고 미네랄을 다 캘 수 있을 때까지 진행
        for (int i = 0; i < minerals.length && limit > 0; i++) {
            if (minerals[i].equals("diamond")) {
                info[i / 5][0] += 1;
                info[i / 5][1] += 5;
                info[i / 5][2] += 25;
            } else if (minerals[i].equals("iron")) {
                info[i / 5][0] += 1;
                info[i / 5][1] += 1;
                info[i / 5][2] += 5;
            } else {
                info[i / 5][0] += 1;
                info[i / 5][1] += 1;
                info[i / 5][2] += 1;
            }

            //광물을 5개를 캤다면 곡괭이 하나 줄이기
            if (i % 5 == 4) limit--;
        }

        //각 구간에서 stone으로 캤을 때 가장 피로도가 높은 것을 다이아 몬드로 캐야하므로 stone의 피로도로 내림차순 정렬
        //stone의 피로도가 같다면 iron으로 내림차순 이외에는 다이아 몬드로 내림차순
        Arrays.sort(info, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] == o2[2]) {
                    if (o1[1] == o2[1]) {
                        return o2[0] - o1[0];
                    }
                    return o2[1] - o1[1];
                }
                return o2[2] - o1[2];
            }
        });

        int answer = 0;
        for (int i = 0, pick = 0; i < info.length; i++) {
            //사용할 곡괭이가 없다면 곡괭이를 쓸 수 있을 때까지 곡괭이 증가
            while (pick < 3 && picks[pick] == 0) pick++;
            //만약 곡괭이 인덱스가 3이라면 쓸 수 없으므로 out
            if (pick == 3) break;

            //사용할 곡괭이 개수 줄이기
            picks[pick]--;
            //피로도 저장
            answer += info[i][pick];
        }

        return answer;
    }
}