import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 학생 수
        m = Integer.parseInt(st.nextToken()); // 도로 수
        x = Integer.parseInt(st.nextToken()); // 파티 장소

        List<Road>[] goParty, backHome = new List[n + 1];
        goParty = new List[n + 1];
        backHome = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            goParty[i] = new ArrayList<>();
            backHome[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            goParty[start].add(new Road(end, time));
            backHome[end].add(new Road(start, time));
        }

        int[] gotime = dijkstra(goParty, x);
        int[] backTime = dijkstra(backHome, x);

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (i == x) continue;

            answer = Math.max(answer, gotime[i] + backTime[i]);
        }

        System.out.println(answer);

    }

    private static int[] dijkstra(List<Road>[] node, int start) {
        PriorityQueue<Road> que = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        int[] time = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(time, 987654321);

        que.offer(new Road(start, 0));
        time[start] = 0;


        while (!que.isEmpty()) {
            Road curr = que.poll();

            if (visited[curr.city]) continue;

            visited[curr.city] = true;

            for (Road nxt : node[curr.city]) {
                if (visited[nxt.city]) continue;

                time[nxt.city] = Math.min(time[nxt.city], time[curr.city] + nxt.time);
                que.offer(new Road(nxt.city, time[nxt.city]));
            }
        }

        return time;
    }
}

class Road {
    int city, time;

    public Road(int city, int time) {
        this.city = city;
        this.time = time;
    }
}
