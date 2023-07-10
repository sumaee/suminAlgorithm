import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String gene = br.readLine();

        int[][] dp = new int[gene.length()][gene.length()];

        //글자 길이를 자를 for문
        for (int len = 1; len < gene.length(); len++) {
            //글자 길이만큼 확인할 i, j
            for (int i = 0; i + len < gene.length(); i++) {
                int j = len + i;
                //유전자 조건을 만족한다면
                if ((gene.charAt(i) == 'a' && gene.charAt(j) == 't') || (gene.charAt(i) == 'g' && gene.charAt(j) == 'c')) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }

                //유전자 조건을 만족시키는 두 유전자를 합쳤을 때를 판단하기 위한 for문
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }

        System.out.println(dp[0][gene.length() - 1]);


    }

}


