import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k, rLen, cLen;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        map = new int[100][100];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rLen = 3;
        cLen = 3;
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        for (int i = 0; i <= 100; i++) {

            if (map[r][c] == k) {
                return i;
            }

            //행의 개수 >= 열의 개수
            if (rLen >= cLen) {
                //모든 행에 대해 수행
                for (int j = 0; j < rLen; j++) {
                    doR(j);
                }
            } else {
                //모든 열에 대해 수행
                for (int j = 0; j < cLen; j++) {
                    doC(j);
                }
            }
        }
        return -1;
    }

    private static void print() {
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void doR(int row) {
        Map<Integer, Integer> cnt = new HashMap<>();
        PriorityQueue<Pair> que = new PriorityQueue<>();

        //개수 세서 넣기
        for (int i = 0; i < cLen; i++) {
            //0이라면 패스
            if (map[row][i] == 0) continue;
            cnt.put(map[row][i], cnt.getOrDefault(map[row][i], 0) + 1);
        }

        cnt.forEach((key, value) -> que.offer(new Pair(key, value)));
        //큐가 빌때까지 수행
        int col = 0;
        while (!que.isEmpty()) {
            Pair curr = que.poll();
            map[row][col++] = curr.num;
            map[row][col++] = curr.count;
        }

        cLen = Math.max(cLen, col);

        while (col < 100) {
            map[row][col++] = 0;
        }

    }

    private static void doC(int col) {
        Map<Integer, Integer> cnt = new HashMap<>();
        PriorityQueue<Pair> que = new PriorityQueue<>();

        //개수 세서 넣기
        for (int i = 0; i < rLen; i++) {
            //0이라면 패스
            if (map[i][col] == 0) continue;
            cnt.put(map[i][col], cnt.getOrDefault(map[i][col], 0) + 1);
        }

        cnt.forEach((key, value) -> que.offer(new Pair(key, value)));
        //큐가 빌때까지 수행
        int row = 0;
        while (!que.isEmpty()) {
            Pair curr = que.poll();
            map[row++][col] = curr.num;
            map[row++][col] = curr.count;
        }

        rLen = Math.max(rLen, row);

        while (row < 100) {
            map[row++][col] = 0;
        }
    }

    static class Pair implements Comparable<Pair> {
        int num, count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            //등장 횟수가 많은 것이 먼저
            if (this.count > o.count) {
                return 1;
            }

            //등장 횟수가 같다면 숫자가 큰것 먼저
            if (this.count == o.count) {
                return this.num - o.num;
            }

            return -1;
        }
    }
}