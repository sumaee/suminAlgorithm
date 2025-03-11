class Solution {
    public int solution(int[][] board, int[][] skill) {
        int [][] sum = new int[board.length+1][board[0].length+1];
        
        for(int[] skillInfo : skill){
            int r1 = skillInfo[1];
            int c1 = skillInfo[2];
            int r2 = skillInfo[3];
            int c2 = skillInfo[4];
            int degree = skillInfo[5] * (skillInfo[0] == 1 ? -1: 1);
            
            sum[r1][c1] += degree;
            sum[r1][c2+1] -= degree;
            sum[r2+1][c1] -= degree;
            sum[r2+1][c2+1] += degree;
        }
        
        for(int i=0; i<board.length ; i++){
            for(int j=1 ; j<board[i].length ; j++){
                sum[i][j] += sum[i][j-1];
            }
        }
        
        for(int i=0; i<board.length ; i++){
            for(int j=1 ; j<board[i].length ; j++){
                sum[j][i] += sum[j-1][i];
            }
        }
                
        int answer = 0;
        for(int i=0; i<board.length ; i++){
            for(int j=0 ; j<board[i].length ; j++){
                if(board[i][j] + sum[i][j] >=1){
                    answer++;
                }
            }
        }
        
        
        return answer;
    }
}