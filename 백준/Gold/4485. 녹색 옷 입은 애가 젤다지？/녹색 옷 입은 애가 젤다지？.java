import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] cave, cost;
    static boolean[][] visited;
    static int n;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) break;
            sb.append("Problem ").append(idx++).append(": ");

            cost = new int[n][n];
            cave = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                Arrays.fill(cost[i], 987654321);
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[n][n];
            dijkstra(0, 0);

            sb.append(cost[n - 1][n - 1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra(int row, int col) {
        PriorityQueue<Node5> que = new PriorityQueue<>((o1, o2) -> o1.money - o2.money);
        visited[row][col] = true;
        cost[row][col] = cave[row][col];
        que.offer(new Node5(row, col, cave[row][col]));

        while (!que.isEmpty()) {
            Node5 curr = que.poll();

            if (curr.row == n - 1 && curr.col == n - 1) break;

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                if (drow < 0 || drow >= n || dcol < 0 || dcol >= n || visited[drow][dcol]) continue;

                visited[drow][dcol] = true;
                cost[drow][dcol] = Math.min(cost[drow][dcol], cost[curr.row][curr.col] + cave[drow][dcol]);
                que.offer(new Node5(drow, dcol, cost[drow][dcol]));
            }

        }
    }
}

class Node5 {
    int row, col, money;

    public Node5(int row, int col, int money) {
        this.row = row;
        this.col = col;
        this.money = money;
    }
}