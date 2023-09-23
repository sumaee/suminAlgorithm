import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dice;
    static int n, m, col, row, k;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[7];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            moveDice(Integer.parseInt(st.nextToken()));
        }

        System.out.println(sb);
    }

    private static void moveDice(int command) {
        int drow = row + dr[command - 1];
        int dcol = col + dc[command - 1];
        //범위를 벗어나는지 확인
        if (drow < 0 || dcol < 0 || drow >= n || dcol >= m) return;

        //굴리기
        rollDice(drow, dcol, command);
        row = drow;
        col = dcol;
    }

    private static void rollDice(int row, int col, int command) {
        //위을 향해 있는 주사위 면
        int under = dice[1];

        //동으로 굴러간다면
        if (command == 1) {
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = under;
        }
        //서로 굴러간다면
        else if (command == 2) {
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = under;
        }
        //북으로 굴러간다면
        else if (command == 3) {
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = under;
        }
        //남으로 굴러간다면
        else {
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = under;
        }

        //지도 위의 숫자가 0이 아니면 다이스에 넣기
        if (map[row][col] != 0) {
            dice[1] = map[row][col];
            map[row][col] = 0;
        } else {
            map[row][col] = dice[1];
        }

        //상단에 써있는 값 구하기
        sb.append(dice[6]).append("\n");
    }
}
