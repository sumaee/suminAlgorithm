import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        List<FoodInfo> foods = new ArrayList<>();
        long total = 0;
        for (int i = 0; i < food_times.length; i++) {
            foods.add(new FoodInfo(i + 1, food_times[i]));
            total += food_times[i];
        }

        //음식을 먹는데 적게 걸리는 시간순으로 정렬
        foods.sort((o1, o2) -> o2.time - o1.time);
        //네트워크 오류 시간이 음식을 다 먹은 후 나타난다면
        if (total <= k) return -1;
        int preFoodCnt = 0;
        for (int i = foods.size() - 1; i >= 0; i--) {
            //현재 음식 개수만큼 사이클을 돌 수 있는지 확인
            long cycle = (long) (foods.get(i).time - preFoodCnt) * foods.size();
            //사이클을 돌 수 있다면
            if (cycle <= k) {
                //사이클 한바퀴돌고
                k -= cycle;
                preFoodCnt = foods.get(i).time;
                foods.remove(i);
            }

            //돌수 없다면
            else {
                //나머지 구하기
                k %= foods.size();
                //남은 음식들 재정렬
                foods.sort((o1, o2) -> o1.idx - o2.idx);
                return foods.get((int) k).idx;
            }
        }

        return -1;
    }
}

class FoodInfo {
    int idx, time;

    public FoodInfo(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }
}