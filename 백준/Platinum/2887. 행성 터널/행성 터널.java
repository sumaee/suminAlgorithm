import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Star> stars;
    static List<Result>[] data;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        stars = new ArrayList<>();
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            stars.add(new Star(i, x, y, z));
        }

        System.out.println(prim(0));
    }

    private static int prim(int start) {
        data = new List[n];
        for (int i = 0; i < n; i++) {
            data[i] = new ArrayList<>();
        }

        //star를 x 축 기준으로 정렬해서 해당 길이를 큐에 추가
        stars.sort((o1, o2) -> o1.x - o2.x);
        for (int i = 0; i < stars.size() - 1; i++) {
            int len = Math.abs(stars.get(i).x - stars.get(i + 1).x);
            data[stars.get(i).idx].add(new Result(stars.get(i + 1).idx, len));
            data[stars.get(i + 1).idx].add(new Result(stars.get(i).idx, len));
        }

        //star를 y 축 기준으로 정렬해서 해당 길이를 큐에 추가
        stars.sort((o1, o2) -> o1.y - o2.y);
        for (int i = 0; i < stars.size() - 1; i++) {
            int len = Math.abs(stars.get(i).y - stars.get(i + 1).y);
            data[stars.get(i).idx].add(new Result(stars.get(i + 1).idx, len));
            data[stars.get(i + 1).idx].add(new Result(stars.get(i).idx, len));
        }

        //star를 z 축 기준으로 정렬해서 해당 길이를 큐에 추가
        stars.sort((o1, o2) -> o1.z - o2.z);
        for (int i = 0; i < stars.size() - 1; i++) {
            int len = Math.abs(stars.get(i).z - stars.get(i + 1).z);
            data[stars.get(i).idx].add(new Result(stars.get(i + 1).idx, len));
            data[stars.get(i + 1).idx].add(new Result(stars.get(i).idx, len));
        }

        int sum = 0;
        PriorityQueue<Result> que = new PriorityQueue<>((o1, o2) -> o1.len - o2.len);
        que.offer(new Result(start, 0));
        while (!que.isEmpty()) {
            Result curr = que.poll();

            if (visited[curr.idx]) continue;

            visited[curr.idx] = true;
            sum += curr.len;

            for (Result nxt : data[curr.idx]) {
                if (!visited[nxt.idx]) {
                    que.offer(nxt);
                }
            }
        }

        return sum;
    }
}

class Result {
    int idx, len;

    public Result(int idx, int len) {
        this.idx = idx;
        this.len = len;
    }
}

class Star {
    int idx, x, y, z;

    public Star(int idx, int x, int y, int z) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
