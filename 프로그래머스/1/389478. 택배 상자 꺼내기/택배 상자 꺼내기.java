class Solution {
    public int solution(int n, int w, int num) {
        int[][] boxes = new int[n/w+1][w];
        
        int r = 0;
        int c = 0;
        int findRow = 0;
        int findCol = 0;
        for(int i=1; i<=n ;i++){
            if(i == num){
                findRow = r;
                findCol = c;
            }
            
            if(r % 2 == 0){
                boxes[r][c] = i;
                c++;
                if(c == w){
                    r++;
                    c--;
                }
            }else{
                boxes[r][c] = i;
                c--;
                if( c == -1){
                    r++;
                    c++;
                }
            }
        }
        
        int answer = 0;
        while(findRow<n/w+1 && boxes[findRow][findCol] != 0){
            answer++;
            findRow++;
        }
        
        return answer;
    }
}