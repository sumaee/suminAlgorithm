import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Task> taskList = new ArrayList<>();
        int lastDay = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            taskList.add(new Task(d, w));
            lastDay = Math.max(lastDay, d);
        }

        PriorityQueue<Task> que = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);
        taskList.sort((o1, o2) -> o2.d - o1.d);
        int score = 0;
        int idx = 0;
        for (int i = lastDay; i >=1 ; i--) {
            while (idx < n && taskList.get(idx).d >= i) {
                que.offer(taskList.get(idx));
                idx++;
            }

            if (!que.isEmpty()) {
                score += que.poll().w;
            }
        }

        System.out.println(score);


    }

    static class Task{
        int d, w;

        public Task(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }
}