import java.util.*;

class Solution {
    final static int INF = 987654321;
    static List<RoadInfo>[] roadInfo;
    static int[] dijkstra;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        dijkstra = new int[n + 1];
        roadInfo = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            roadInfo[i] = new ArrayList<>();
            dijkstra[i] = INF;
        }

        //길 정보 입력
        for (int i = 0; i < paths.length; i++) {
            roadInfo[paths[i][0]].add(new RoadInfo(paths[i][1], paths[i][2]));
            roadInfo[paths[i][1]].add(new RoadInfo(paths[i][0], paths[i][2]));
        }

        //출발지 정보 저장
        Set<Integer> gateSet = new HashSet<>();
        for (int gate : gates) {
            gateSet.add(gate);
        }

        //봉우리 정보 저장
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }

        //출발지마다 정보 확인
        for (int gate : gates) {
            check(gate, gateSet, summitSet);
        }


        int[] answer = new int[2];
        int result = INF;
        for (int i = 1; i < dijkstra.length; i++) {
            //산봉우리라면
            if (summitSet.contains(i)) {
                if (dijkstra[i] < result) {
                    result = dijkstra[i];
                    answer[0] = i;
                    answer[1] = result;
                }
            }
        }
        return answer;
    }
    private static void check(int gate, Set<Integer> gateSet, Set<Integer> summintSet) {
        Queue<RoadInfo> que = new LinkedList<>();
        que.offer(new RoadInfo(gate, 0));
        dijkstra[gate] = 0;

        while (!que.isEmpty()) {
            RoadInfo curr = que.poll();

            //봉우리라면 패스
            if (summintSet.contains(curr.to)) continue;

            for (RoadInfo to : roadInfo[curr.to]) {
                //다음 갈길이 다른 출발지라면 패스
                if (gateSet.contains(to.to)) continue;

                //지금까지 최고의 시간과 다음 봉우리까지 가는데 걸리는 시간중 최고 시간 기록
                int maxValue = Math.max(to.time, dijkstra[curr.to]);

                //만약 지금까지 최고의 시간이 다음에 갈 곳에 저장된 기록보다 작다면 갱신
                if (maxValue < dijkstra[to.to]) {
                    dijkstra[to.to] = maxValue;
                    que.offer(new RoadInfo(to.to, maxValue));
                }
            }
        }
    }
}
class RoadInfo {
        int to, time;

        public RoadInfo(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }