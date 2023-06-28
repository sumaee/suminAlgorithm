import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();

        int weightSum = 0;
        int answer = 0;
        for (int i = 0; i < truck_weights.length; i++) {

            //가능할 때까지 진행
            while (true) {
                //큐가 비어있다면 넣고 while 문 끝
                if (bridge.isEmpty()) {
                    bridge.offer(truck_weights[i]);
                    weightSum += truck_weights[i];
                    answer++;
                    break;
                }

                //다리의 길이만큼 트럭이 올라가있는 경우에는 맨 앞에 있는 값 빼기
                else if (bridge.size() == bridge_length) {
                    weightSum -= bridge.poll();
                }
                // 올라갈 수 있다면 다리 위의 무게 확인
                else {
                    //무게가 넘는다면
                    if (weightSum + truck_weights[i] > weight) {
                        //0을 넣어 그냥 보내기
                        bridge.offer(0);
                        answer++;
                    }
                    //더 들어갈 수 있다면 큐에 값을 넣고 while문 끝내기
                    else {
                        bridge.offer(truck_weights[i]);
                        weightSum += truck_weights[i];
                        answer++;
                        break;
                    }
                }

            }
        }
        //마지막 값은 들어가고 끝이므로 그 값또한 다리를 지날 수 있도록 더해주기
        answer += bridge_length;
        return answer;
    }
}