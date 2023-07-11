import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //양수 위치는 내림차순
        PriorityQueue<Integer> posQue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //음수 위치는 절대값 기준 내림차순
        PriorityQueue<Integer> negQue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num < 0) negQue.offer(num * -1);
            else posQue.offer(num);
        }

        //멀리있는 책을 저장
        int maxLen;
        if (posQue.isEmpty()) maxLen = negQue.peek();
        else if (negQue.isEmpty()) maxLen = posQue.peek();
        else maxLen = Math.max(negQue.peek(), posQue.peek());

        //양수 위치의 책 정리
        int answer = 0;
        while (!posQue.isEmpty()) {
            int curr = posQue.poll();
            // curr이 제일 먼 곳이므로 같이 들고 갈 위치는 빼주기
            for (int i = 0; i < m - 1; i++) {
                posQue.poll();

                //빼다가 큐가 비었으면 끝
                if (posQue.isEmpty()) break;
            }

            answer += curr * 2;
        }

        //음수 위치 정리
        while (!negQue.isEmpty()) {
            int curr = negQue.poll();
            //curr이 제일 먼곳이므로 같이 들고 갈 위치 빼주기
            for (int i = 0; i < m - 1; i++) {
                negQue.poll();

                //빼다가 큐가 비었으면 끝
                if (negQue.isEmpty()) break;
            }

            answer += curr * 2;
        }

        //위의 로직으로는 제일 먼 곳까지 왔다갔다를 해줬는데 제일 먼곳은 한번만 가면 되므로 빼주기
        answer -= maxLen;
        System.out.println(answer);
    }
}
