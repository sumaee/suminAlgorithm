import java.lang.*;
import java.util.*;

class Solution {
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2];
        quadTree(arr, 0, 0, arr.length);
        return answer;
    }

    private static void quadTree(int[][] arr, int row, int col, int size) {
        int sum = 0;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                sum += arr[i][j];
            }
        }

        if (sum == 0) {
            answer[0]++;
            return;
        }

        if (sum == size * size) {
            answer[1]++;
            return;
        }

        quadTree(arr, row, col, size / 2);
        quadTree(arr, row, col + (size / 2), size / 2);
        quadTree(arr, row + (size / 2), col + (size / 2), size / 2);
        quadTree(arr, row + (size / 2), col, size / 2);
    }
}
