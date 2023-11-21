import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
재채점 틀림:
1 1
A
0
정답 : 1
 */

public class Main {
    static int r, c, answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        char[][] board = new char[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }

        //방문했던 알파벳을 판단하기 위한 작업
        check = new boolean[26];
        answer = 1;
        dfs(board, 0, 0, 0);
        System.out.println(answer);
    }

    private static void dfs(char[][] board, int row, int col, int cnt) {
        //이미 방문한 알파벳이라면 정답 갱신 후 끝
        if (check[board[row][col] - 'A']) {
            answer = Math.max(answer, cnt);
            return;
        }

        //알파벳 방문처리 후 상하좌우 탐색
        check[board[row][col] - 'A'] = true;
        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //범위를 벗어난 다면 패스
            if (drow < 0 || drow >= r || dcol < 0 || dcol >= c)
                continue;

            dfs(board, drow, dcol, cnt + 1);
        }

        //방문처리 해제
        check[board[row][col] - 'A'] = false;

    }
}