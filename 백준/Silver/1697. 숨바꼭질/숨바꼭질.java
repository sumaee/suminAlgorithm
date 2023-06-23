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

        int[] move = {-1, 1, 2};

        Queue<Integer> que = new LinkedList<>();
        int[] visited = new int[100001];

        que.offer(now);
        visited[now] = 1;

        while (!que.isEmpty()) {
            int currIdx = que.poll();
            //같다면 끝
            if (currIdx == bro) {
                System.out.println(visited[currIdx]-1);
                break;
            }

            for (int i = 0; i < 3; i++) {
                int nxtIdx;
                //순간이동과 한칸 앞뒤 움직인것 담기
                if (move[i] == 2) nxtIdx = currIdx * move[i];
                else nxtIdx = currIdx + move[i];

                //만약 움직인 거리가 범위를 안벗어나고 방문하지 않았다면 큐에 넣기
                if (nxtIdx >= 0 && nxtIdx < 100001 && visited[nxtIdx] == 0) {
                    que.add(nxtIdx);
                    visited[nxtIdx] = visited[currIdx] + 1;
                }
            }

        }

    }
}