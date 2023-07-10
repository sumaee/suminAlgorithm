import java.lang.*;
import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int row = board.length;
        int col = board[0].length;
        int[][] sum = new int[row + 1][col + 1];

        int max = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (board[i - 1][j - 1] == 1) {
                    sum[i][j] = Math.min(Math.min(sum[i - 1][j], sum[i][j - 1]), sum[i - 1][j - 1]) + 1;
                    max = Math.max(max, sum[i][j]);
                }
            }
        }
        
        return max*max;
    }
}