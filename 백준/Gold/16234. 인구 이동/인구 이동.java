import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, l, r;
    static int[][] country, tmp;
    static boolean[][] connected;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        country = new int[n][n];
        tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
                tmp[i][j] = country[i][j];
            }
        }

        int answer = 0;
        while (true) {
            //배열을 돌면서 l이상 r이하만큼 차이나는 곳이 있다면 해당 지역에서 bfs 실행
            connected = new boolean[n][n];
            flag = false;
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!connected[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            if (!flag) {
                break;
            }
            country = tmp;
            answer++;
        }
        System.out.println(answer);
    }

    private static void bfs(int row, int col) {
        List<Node> list = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        connected[row][col] = true;
        que.offer(new Node(row, col));
        list.add(new Node(row, col));

        int sum = country[row][col];
        int cnt = 1;
        while (!que.isEmpty()) {
            Node curr = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = curr.row + dr[i];
                int dcol = curr.col + dc[i];

                //범위 벗어나면 다음
                if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || connected[drow][dcol]) continue;

                //해당 지역이 현재 지역과 l이상 r이하 차이나는지 확인
                int diff = Math.abs(country[curr.row][curr.col] - country[drow][dcol]);
                if (diff >= l && diff <= r) {
                    flag = true;
                    connected[drow][dcol] = true;
                    que.offer(new Node(drow, dcol));
                    list.add(new Node(drow, dcol));
                    sum += country[drow][dcol];
                    cnt++;
                }
            }
        }

        //변경 된 값 구하기
        int change = sum / cnt;
        //연결된 곳들을 돌며 배열 숫자 바꿔주기
        for (Node node : list) {
            tmp[node.row][node.col] = change;
        }

    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}