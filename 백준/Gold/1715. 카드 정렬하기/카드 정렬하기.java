import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> que = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            que.offer(Integer.parseInt(br.readLine()));
        }

        int sum = 0;
         if (que.size() == 1) {
            System.out.println(0);
            return;
        }
        while (true) {
            int first = que.poll();
            if (que.size() == 1) {
                sum += first + que.poll();
                break;
            } else if (que.size() == 0) {
                sum += first;
                break;
            }

            int second = que.poll();
            sum += first + second;
            que.offer(first + second);
        }

        System.out.println(sum);
    }
}
