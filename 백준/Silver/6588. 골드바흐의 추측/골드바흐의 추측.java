import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] primeNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        primeNum = new boolean[1000001];
        checkPrimeNum();

        while (true) {
            int num = Integer.parseInt(br.readLine());

            //0이면 끝내기
            if (num == 0) break;
            boolean check = false;
            for (int i = 2; i <= num / 2; i++) {
                if (!primeNum[i] && !primeNum[num - i]) {
                    sb.append(num).append(" = ").append(i).append(" + ").append(num - i).append("\n");
                    check = true;
                    break;
                }
            }

            if (!check) {
                sb.append("Goldbach's conjecture is wrong.").append("\n");
            }

        }

        System.out.println(sb);
    }

    private static void checkPrimeNum() {
        //false : 소수
        for (int i = 2; i <= 1000000; i++) {
            if (primeNum[i]) continue;

            for (int j = 2 * i; j <= 1000000; j += i) {
                primeNum[j] = true;
            }
        }
    }
}
