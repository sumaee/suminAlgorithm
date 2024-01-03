import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int f, s, g, u, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        int answer = bfs();
        System.out.println(answer == -1 ? "use the stairs" : answer);
    }

    private static int bfs() {
        Queue<Floors> que = new LinkedList<>();
        boolean[] visited = new boolean[f + 1];

        que.offer(new Floors(s, 0));
        visited[s] = true;

        while (!que.isEmpty()) {
            Floors curr = que.poll();

            //도착지라면 끝
            if (curr.floor == g) {
                return curr.cnt;
            }

            //위로 두칸 이동했을 때 건물 범위 안에 있다면 넣기
            if (curr.floor + u <= f && !visited[curr.floor + u]) {
                que.offer(new Floors(curr.floor + u, curr.cnt + 1));
                visited[curr.floor + u] = true;
            }

            //아래를 갔을 때 건물 범위 안에 있다면 넣기
            if (curr.floor - d >= 1 && !visited[curr.floor - d]) {
                que.offer(new Floors(curr.floor - d, curr.cnt + 1));
                visited[curr.floor - d] = true;
            }
        }

        //여기 까지 왔다는건 도달 못함
        return -1;
    }

    static class Floors {
        int floor, cnt;

        public Floors(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
    }
}