import java.lang.*;
import java.util.*;

class Solution {
    static Map<Long, Long> roomInfo;
    public long[] solution(long k, long[] room_number) {
         // (배정받은 번호, 다음 비어있는 번호)
        roomInfo = new HashMap<>();
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = getRoomIdx(room_number[i]);
        }
        return answer;
    }

    private static long getRoomIdx(long currIdx) {
        //원하는 방이 비어있는 방이라면
        if (!roomInfo.containsKey(currIdx)) {
            // (현재 배정받은 방, 다음 비어있는 방)으로 저장하고 현재 번호 리턴
            roomInfo.put(currIdx, currIdx + 1);
            return currIdx;
        }

        //원하는 방이 비어있는 방이 아니라면 다음 빈 방을 찾아 저장
        long nxtIdx = getRoomIdx(roomInfo.get(currIdx));
        roomInfo.put(currIdx, nxtIdx);
        return nxtIdx;
    }
}
