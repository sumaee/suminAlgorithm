class Solution {
    public int[] solution(int n) {
         int[][] arr = new int[n][n];

        //채울 숫자는 1~n까지 더한 숫자
        int limit = 0;
        for (int i = 1; i <= n; i++) {
            limit += i;
        }

        int[] dr = {1, 0, -1}; // 아래, 오른쪽, 대각선 위 이동
        int[] dc = {0, 1, -1}; // 아래, 오른쪽, 대각선 위 이동

        int num = 1;
        int row = 0;
        int col = 0;
        int idx = 0;
        while (num <= limit) {
            arr[row][col] = num++;

            int drow = row + dr[idx];
            int dcol = col + dc[idx];
            //방향전환이 필요하다면
            if (drow == n || dcol == n || arr[drow][dcol] != 0) {
                idx = (idx + 1) % 3;
                drow = row + dr[idx];
                dcol = col + dc[idx];

                //방향 전환하고도 채울것이 없으면 끝
                if (drow == n || dcol == n || arr[drow][dcol] != 0) {
                    break;
                }
            }
            row = drow;
            col = dcol;
        }

        int[] answer = new int[limit];
        idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = arr[i][j];
            }
        }

        return answer;
    }
}