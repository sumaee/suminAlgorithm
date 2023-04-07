import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] coin;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 세로 길이
        int m = Integer.parseInt(st.nextToken()); // 가로 길이

        coin = new int[n][m];

        for (int i = 0; i < coin.length; i++) {
            String str = br.readLine();
            for(int j=0; j<coin[i].length ; j++){
                coin[i][j] = str.charAt(j) - '0';
            }
        }

        //배열 거꾸로 돌면서 1을 만나면 다 돌리기
        int answer = 0;
        for(int i=coin.length-1 ; i>=0 ; i--){
            for (int j = coin[i].length - 1; j >= 0; j--) {
                if (coin[i][j] == 1) {
                    changeCoin(i, j);
                    answer ++;
                }
            }
        }

        System.out.println(answer);
    }

    private static void changeCoin(int row, int col){
        for (int i = row; i >= 0; i--) {
            for (int j = col; j >= 0; j--) {
                if(coin[i][j] == 1)
                    coin[i][j] =0;
                else coin[i][j] = 1;
            }
        }
    }
}