import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, t, g, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        answer = -1;
        bfs();

        System.out.println(answer == -1 ? "ANG" : answer);
    }

    private static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(n);

        boolean[] visited = new boolean[100000];
        visited[n] = true;

        int[] clickCnt = new int[100000];

        while (!que.isEmpty()) {
            int curr = que.poll();

            //목적지 도착이면 갱신
            if (curr == g) {
                answer = clickCnt[curr];
            }

            //지금 누른 횟수에 한번 더 눌렀을 때 한계치를 넘어간다면 다음꺼 보기
            if (clickCnt[curr] + 1 > t) continue;

            //A 버튼 눌렀을 때
            int aButton = curr + 1;
            if (aButton < 100000 && !visited[aButton]) {
                visited[aButton] = true;
                clickCnt[aButton] = clickCnt[curr] + 1;
                que.add(aButton);
            }

            //B 버튼 눌렀을 때
            int bButton = curr * 2;
            if (bButton > 0 && bButton < 100000) {
                //조건에 맞게 숫자 변경해주기
                bButton = changeNum(bButton);
                if (!visited[bButton]) {
                    visited[bButton] = true;
                    clickCnt[bButton] = clickCnt[curr] + 1;
                    que.offer(bButton);
                }
            }
        }
    }

    private static int changeNum(int b) {

        //해당 숫자 일단 각 자리수에 맞게 변경해주고 0이 아닌 숫자가 나오는 곳 표시 -> 제일 큰 자릿수 찾기
        int[] nums = new int[5];
        int idx = 5;
        for (int i = 0, k = 10000; i < 5; i++, k /= 10) {
            nums[i] = (b / k) % 10;

            if (nums[i] != 0) {
                idx = Math.min(idx, i);
            }
        }

        //제일 큰 자릿수 숫자 하나 줄이기
        nums[idx]--;

        //다시 숫자로 변환
        int result = 0;
        for (int i = 0, k = 10000; i < 5; i++, k /= 10) {
            result += nums[i] * k;
        }

        return result;
    }
}
