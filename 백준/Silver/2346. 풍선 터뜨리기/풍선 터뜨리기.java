import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Deque<Balloon> que = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            que.offer(new Balloon(i + 1, Integer.parseInt(st.nextToken())));
        }

        Balloon balloon = que.pollFirst();
        sb.append(balloon.idx).append(" ");

        while (!que.isEmpty()) {

            if (balloon.num > 0) {
                for (int i = 1; i < balloon.num; i++) {
                    que.offer(que.pollFirst());
                }

                balloon = que.pollFirst();
                sb.append(balloon.idx).append(" ");
            } else {
                for (int i = 1; i < -balloon.num; i++) {
                    que.offerFirst(que.pollLast());
                }

                balloon = que.pollLast();
                sb.append(balloon.idx).append(" ");
            }
        }

        System.out.println(sb);
    }

    static class Balloon {
        int idx;
        int num;

        public Balloon(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}