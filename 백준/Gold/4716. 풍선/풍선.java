import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (n == 0 && a == 0 && b == 0) {
                break;
            }

            PriorityQueue<TeamInfo> que = new PriorityQueue<>((o1, o2) -> Math.abs(o2.distA - o2.distB) - Math.abs(o1.distA - o1.distB));
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int distA = Integer.parseInt(st.nextToken());
                int distB = Integer.parseInt(st.nextToken());

                que.offer(new TeamInfo(k, distA, distB));
            }
            int sum = 0;

            while (!que.isEmpty()) {
                TeamInfo curr = que.poll();

                if (curr.distA < curr.distB) {
                    if (a < curr.k) {
                        sum += curr.distA * a;
                        sum += curr.distB * (curr.k - a);
                        b -= (curr.k - a);
                        a = 0;
                    } else {
                        sum += curr.distA * curr.k;
                        a -= curr.k;
                    }
                } else {
                    if (b < curr.k) {
                        sum += curr.distB * b;
                        sum += curr.distA * (curr.k - b);
                        a -= (curr.k - b);
                        b = 0;
                    } else {
                        sum += curr.distB * curr.k;
                        b -= curr.k;
                    }
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    static class TeamInfo {
        int k, distA, distB;

        public TeamInfo(int k, int distA, int distB) {
            this.k = k;
            this.distA = distA;
            this.distB = distB;
        }
    }
}