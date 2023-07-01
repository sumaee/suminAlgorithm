import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, w;
    static int[][] dp;
    static Locate1[] events;
    static Queue<Integer> police;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 도로 개수
        w = Integer.parseInt(br.readLine()); // 사건 개수

        events = new Locate1[w + 1];
        dp = new int[w + 1][w + 1];
        police = new LinkedList<>();

        for (int i = 1; i <= w; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            events[i] = new Locate1(x, y);
        }

        for (int i = 0; i <= w; i++) {
            Arrays.fill(dp[i], -1);
        }

        //이동거리 구하기
        int dis = getDis(0, 0);
        sb.append(dis).append("\n");

        //어떤 경찰차가 이동하는지 구하기
        getPath(0, 0);

        while (!police.isEmpty()) {
            sb.append(police.poll()).append("\n");
        }
        System.out.println(sb);
    }

    private static void getPath(int event1, int event2) {
        //만약 모든 이벤트를 처리했다면 끝
        if (event1 == w || event2 == w) {
            return;
        }

        //다음 사건 발생
        int nxt = Math.max(event1, event2) + 1;

        // 경찰차 1번과의 다음 사건 지점 과의 거리
        int dis1;
        //만약 출발지에서 출발을 한다면
        if (event1 == 0) {
            dis1 = calDis(1, 1, events[nxt]);
        } else {
            dis1 = calDis(events[event1].x, events[event1].y, events[nxt]);
        }

        //경찰차 2번이 처리하는 경우
        int dis2;
        //만약 출발지에서 출발을 한다면
        if (event2 == 0) {
            dis2 = calDis(n, n, events[nxt]);
        } else {
            dis2 = calDis(events[event2].x, events[event2].y, events[nxt]);
        }

        //1번이 처리하는게 빠르다면 1번 처리
        if (dp[nxt][event2] + dis1 < dp[event1][nxt] + dis2) {
            police.add(1);
            getPath(nxt, event2);
        } else {
            police.add(2);
            getPath(event1, nxt);
        }
    }

    private static int getDis(int event1, int event2) {
        //만약 모든 이벤트를 처리했다면 끝
        if (event1 == w || event2 == w) {
            return 0;
        }

        //이미 계산 된거라면 더 볼 필요 없음
        if (dp[event1][event2] != -1) return dp[event1][event2];

        //다음 사건 발생
        int nxt = Math.max(event1, event2) + 1;

        // 경찰차 1번과의 다음 사건 지점 과의 거리
        int dis1;
        //만약 출발지에서 출발을 한다면
        if (event1 == 0) {
            dis1 = calDis(1, 1, events[nxt]);
        } else {
            dis1 = calDis(events[event1].x, events[event1].y, events[nxt]);
        }
        int result1 = dis1 + getDis(nxt, event2);

        //경찰차 2번이 처리하는 경우
        int dis2;
        //만약 출발지에서 출발을 한다면
        if (event2 == 0) {
            dis2 = calDis(n, n, events[nxt]);
        } else {
            dis2 = calDis(events[event2].x, events[event2].y, events[nxt]);
        }
        int result2 = dis2 + getDis(event1, nxt);

        //둘 중 제일 가까운 거리를 저장
        return dp[event1][event2] = Math.min(result1, result2);
    }

    private static int calDis(int x, int y, Locate1 event) {
        return Math.abs(event.x - x) + Math.abs(event.y - y);
    }
}

class Locate1 {
    int x, y;

    public Locate1(int x, int y) {
        this.x = x;
        this.y = y;
    }
}