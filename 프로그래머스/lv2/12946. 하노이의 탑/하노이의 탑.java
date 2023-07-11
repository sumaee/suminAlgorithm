import java.lang.*;
import java.util.*;

class Solution {
    static int result;
    static List<Pair> list;
    public int[][] solution(int n) {
        list = new ArrayList<>();
        hanoiTop(1, 2, 3, n);
        
        int[][] answer = new int[result][2];
        for(int i=0; i<result ; i++){
            answer[i][0] = list.get(i).prev;
            answer[i][1] = list.get(i).nxt;
        }
        return answer;
    }

    private static void hanoiTop(int start, int mid, int end, int cnt) {
        //이동할 블럭이 한개일 경우 이동하고 return
        if (cnt == 1) {
            result++;
            list.add(new Pair(start, end));
            return;
        }

        //첫번째에서 경유지 판으로 옮기기
        hanoiTop(start, end, mid, cnt - 1);
        list.add(new Pair(start, end));
        result++;
        //경유지에 있는 판을 목적지 판으로 옮기기
        hanoiTop(mid, start, end, cnt - 1);
    }
}
class Pair{
    int prev, nxt;
    
    public Pair(int prev, int nxt){
        this.prev = prev;
        this.nxt = nxt;
    }
}