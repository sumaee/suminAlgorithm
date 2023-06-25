import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Node2> que;
    static int[][] tomatos;
    static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        que = new LinkedList<>();
        tomatos = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                tomatos[i][j] = tomato;

                if (tomato == 1) {
                    que.offer(new Node2(i, j, 0));
                }
            }
        }
        if (que.isEmpty()) {
            System.out.println(-1);
        } else {
            bfs();
        }
    }

    private static void bfs() {

        int answer = 0;
        while (!que.isEmpty()) {
            Node2 curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위를 벗어나거나 -1 이라면 패스
                if (drow < 0 || drow >= n || dcol < 0 || dcol >= m || tomatos[drow][dcol] == -1) continue;

                if (tomatos[drow][dcol] == 0) {
                    que.offer(new Node2(drow, dcol, curr.day + 1));
                    tomatos[drow][dcol] = 1;
                }
                answer = Math.max(answer, curr.day);
            }
        }

        //토마토 부분 돌면서 안익은 토마토 가 있다면 -1 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomatos[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);
    }
}

class Node2 {
    int row, col, day;

    public Node2(int row, int col, int day) {
        this.row = row;
        this.col = col;
        this.day = day;
    }
}
