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
        PriorityQueue<NumInfo> aPq = new PriorityQueue<>((o1, o2) -> o1.num == o2.num ? o1.idx - o2.idx : o2.num - o1.num);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            aPq.offer(new NumInfo(Integer.parseInt(st.nextToken()), i));
        }

        int m = Integer.parseInt(br.readLine());
        int[] bArr = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<NumInfo> bPq = new PriorityQueue<>((o1, o2) -> o1.num == o2.num ? o1.idx - o2.idx : o2.num - o1.num);

        int start = 0;
        int idx = -1;

        while (!aPq.isEmpty()) {
            NumInfo nxt = aPq.poll();

            for (int i = start; i < m; i++) {
                if (nxt.num == bArr[i] && idx < nxt.idx) {
                    bPq.offer(new NumInfo(nxt.num, i));

                    idx = nxt.idx;
                    start = i + 1;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(bPq.size()).append("\n");
        while (!bPq.isEmpty()) {
            sb.append(bPq.poll().num).append(" ");
        }

        System.out.println(sb);
    }

    static class NumInfo {
        int num, idx;

        public NumInfo(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}
