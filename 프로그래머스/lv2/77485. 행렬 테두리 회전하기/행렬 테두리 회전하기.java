import java.lang.*;
import java.util.*;

class Solution {
    int[] answer;
    int idx;
    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        //일단 행렬 만들기
        int[][] matrix = new int[rows][columns];

        int num = 1;
        for(int i=0; i<rows ; i++){
            for(int j=0; j<columns ; j++){
                matrix[i][j] = num++;
            }
        }

        for (int[] query : queries) {
            matrix = changeMatrix(query, matrix);
        }

       return answer;
    }

    private int[][] changeMatrix(int[] query, int[][] matrix) {
        int row1 = query[0]-1;
        int col1 = query[1]-1;
        int row2 = query[2]-1;
        int col2 = query[3]-1;

        int firstTmp = matrix[row1+1][col1];
        int smallNum = firstTmp;

        //배열 회전하면서 작은 값 찾기
        for (int i = col1; i <= col2; i++) {
            int tmp = matrix[row1][i];
            matrix[row1][i] = firstTmp;
            firstTmp = tmp;
            smallNum = findSmallNum(tmp, smallNum);
        }

        for (int i = row1 + 1; i <= row2; i++) {
            int tmp = matrix[i][col2];
            matrix[i][col2] = firstTmp;
            firstTmp = tmp;
            smallNum = findSmallNum(tmp, smallNum);
        }

        for(int i = col2 -1 ; i>=col1 ; i--){
            int tmp = matrix[row2][i];
            matrix[row2][i] = firstTmp;
            firstTmp = tmp;
            smallNum = findSmallNum(tmp, smallNum);
        }

        for (int i = row2 - 1; i >= row1; i--) {
            int tmp = matrix[i][col1];
            matrix[i][col1] = firstTmp;
            firstTmp = tmp;
            smallNum = findSmallNum(tmp, smallNum);
        }

        answer[idx++] = smallNum;
        return matrix;
    }

    private int findSmallNum(int tmp, int smallNum) {
        if (tmp < smallNum) {
            smallNum = tmp;
        }
        return smallNum;
    }
}
