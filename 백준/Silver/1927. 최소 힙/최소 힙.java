import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> que = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            //작은 값 뽑아내기
            if (num == 0) {
                //큐가 비어있다면 0 출력
                if (que.isEmpty()) sb.append(0).append("\n");
                else sb.append(que.poll()).append("\n");
            }

            //큐에 넣기
            else {
                que.offer(num);
            }
        }
        System.out.println(sb);
    }
}
