import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int now = Integer.parseInt(st.nextToken());
        int bro = Integer.parseInt(st.nextToken());

        if (bro <= now) {
            System.out.println(now - bro + "\n1");
            return;
        }

        int[] move = {-1, 1, 2};

        Queue<Integer> que = new LinkedList<>();
        int[] visited = new int[100001];

        que.offer(now);
        visited[now] = 1;

        int answer = Integer.MAX_VALUE;
        int cnt = 0;
        while (!que.isEmpty()) {
            int currIdx = que.poll();

            if (visited[currIdx] > answer) break;

            for (int i = 0; i < 3; i++) {
                int nxtIdx;
                //순간이동과 한칸 앞뒤 움직인것 담기
                if (move[i] == 2) nxtIdx = currIdx * move[i];
                else nxtIdx = currIdx + move[i];

                //만약 움직인 거리가 범위를 벗어나면 패스
                if (nxtIdx < 0 || nxtIdx > 100000) continue;

                if (nxtIdx == bro) {
                    answer = visited[currIdx];
                    cnt++;
                }

                //첫 방문이거나 현재 시간+1과 같은 시간대에 방문했다면 넣어주기
                if (visited[nxtIdx] == 0 || visited[currIdx] + 1 == visited[nxtIdx]) {
                    que.offer(nxtIdx);
                    visited[nxtIdx] = visited[currIdx] + 1;
                }
            }

        }
        System.out.println(answer + "\n" + cnt);

    }
}