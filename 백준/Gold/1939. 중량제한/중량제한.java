import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static List<Bridge>[] bridges;
    static boolean[] visited;
    static int firstFac, secondFac;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 섬의 개수
        m = Integer.parseInt(st.nextToken()); // 다리의 개수

        bridges = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            bridges[i] = new ArrayList<>();
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());

            bridges[start].add(new Bridge(end, limit));
            bridges[end].add(new Bridge(start, limit));

            max = Math.max(max, limit);
        }

        st = new StringTokenizer(br.readLine());
        firstFac = Integer.parseInt(st.nextToken());
        secondFac = Integer.parseInt(st.nextToken());

        int min = 1;
        int answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;

            //만약 해당 중량으로 다리 건너기가 가능하다면 중량 늘리기
            if (bfs(mid)) {
                min = mid + 1;
                answer = Math.max(answer, mid);
            }
            //불가능 하다면 중량 줄이기
            else {
                max = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean bfs(int mid) {
        Queue<Integer> que = new LinkedList<>();
        visited = new boolean[n + 1];
        visited[firstFac] = true;

        que.offer(firstFac);
        while (!que.isEmpty()) {
            int curr = que.poll();

            //현재 좌표가 도착지라면 true 반환하기
            if (curr == secondFac) {
                return true;
            }

            //아니라면 지금 좌표와 연결된 점을 돌면서 확인
            for (Bridge bridge : bridges[curr]) {
                //다음 지점이 방문을 안했었고 현재 mid 중량을 버틸 수 있다면
                if (!visited[bridge.n] && bridge.limit >= mid) {
                    visited[bridge.n] = true;
                    que.offer(bridge.n);
                }
            }

        }

        //while문에서 return true에 안걸렸다는 것은 mid 중량으로 해당 목적지까지 못간다는 뜻이므로
        return false;
    }
}

class Bridge {
    int n, limit;

    public Bridge(int n, int limit) {
        this.n = n;
        this.limit = limit;
    }
}