import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        //0을 안지나는 버스 저장
        List<Bus> busesA = new ArrayList<>();
        //0을 지나는 버스 저장
        List<Bus> busesB = new ArrayList<>();
        int minStart = Integer.MAX_VALUE;
        int maxFinish = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a < b) {
                busesA.add(new Bus(i, a, b));
            } else {
                minStart = Math.min(minStart, a);
                maxFinish = Math.max(maxFinish, b);
                b += n;

                busesB.add(new Bus(i, a, b));
            }
        }

        busesA.sort((o1, o2) -> o1.a == o2.a ? o2.b - o1.b : o1.a - o2.a);
        busesB.sort((o1, o2) -> o1.a == o2.a ? o2.b - o1.b : o1.a - o2.a);

        int idx = 0;
        boolean[] delete = new boolean[m + 1];
        //0 을 안지나는 버스 노선 처리
        for (int i = 0; i < busesA.size(); i++) {
            Bus curr = busesA.get(i);
            if (minStart <= curr.a) delete[curr.idx] = true;
            if (maxFinish >= curr.b) delete[curr.idx] = true;

            if (curr.b > idx) {
                idx = curr.b;
            } else {
                delete[curr.idx] = true;
            }
        }

        //0을 지나는 버스 노선 처리
        idx = 0;
        for (int i = 0; i < busesB.size(); i++) {
            Bus curr = busesB.get(i);

            if (curr.b > idx) {
                idx = curr.b;
            } else {
                delete[curr.idx] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= m; i++) {
            if (!delete[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);

    }

    static class Bus {
        int idx, a, b;

        public Bus(int idx, int a, int b) {
            this.idx = idx;
            this.a = a;
            this.b = b;
        }
    }
}