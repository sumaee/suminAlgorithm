import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Info> que = new PriorityQueue<>(
                (o1, o2) -> o1.t * o2.s == o2.t * o1.s ? o1.idx - o2.idx : o1.t * o2.s - o2.t * o1.s
        );

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            que.offer(new Info(t, s, i + 1));
        }

        StringBuilder sb = new StringBuilder();
        while (!que.isEmpty()) {
            sb.append(que.poll().idx).append(" ");
        }

        System.out.println(sb);
    }

    static class Info {
        int t, s, idx;

        public Info(int t, int s, int idx) {
            this.t = t;
            this.s = s;
            this.idx = idx;
        }
    }
}