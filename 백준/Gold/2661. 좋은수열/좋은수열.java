import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        findNum("", 0);
    }

    private static void findNum(String num, int cnt) {
        if (cnt == n) {
            System.out.println(num);
            System.exit(0);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (isPossible(num + i)) {
                findNum(num + i, cnt + 1);
            }
        }

    }

    private static boolean isPossible(String check) {
        //마지막부터 확인해서 마지막 1개의 숫자가 바로 앞 1개의 숫자랑 같은지확인
        // 다음에는 마지막 2개의 숫자가 바로 앞 2개의 숫자랑 같은지 확인
        // 위의 방식을 절반의 길이까지 계속 반복
        for (int i = 1; i <= check.length() / 2; i++) {
            if (check.substring(check.length() - i).equals(check.substring(check.length() - 2 * i, check.length() - i))) {
                return false;
            }
        }
        return true;
    }
}
