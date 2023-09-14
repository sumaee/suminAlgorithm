import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static PriorityQueue<Time> que, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        que = new PriorityQueue<>((o1, o2) -> o1.startTime == o2.startTime ? o1.endTime - o2.endTime : o1.startTime - o2.startTime);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int classNum = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            que.offer(new Time(classNum, startTime, endTime));
        }

        answer = new PriorityQueue<>((o1, o2) -> o1.endTime - o2.endTime);
        answer.offer(que.poll());
        System.out.println(checkRoom());
    }

    private static int checkRoom() {

        while (!que.isEmpty()) {
            Time nxt = que.poll();
            Time curr = answer.peek();

            //다음 강의 시작 시간이 현재 강의 끝나는 시간보다 늦으면 answer에 있는 현재 강의를 빼고 넣기
            if (nxt.startTime >= curr.endTime) {
                answer.poll();
            }

            answer.offer(nxt);
        }

        return answer.size();
    }

    static class Time {
        int classNum, startTime, endTime;

        public Time(int classNum, int startTime, int endTime) {
            this.classNum = classNum;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
