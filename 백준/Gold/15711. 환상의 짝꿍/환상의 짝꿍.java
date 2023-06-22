import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[] primeNum;
    static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        primeNum = new boolean[2000001];
        primes = new ArrayList<>();
        getPrimeNum();
        nxt:
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong((st.nextToken()));
            long b = Long.parseLong((st.nextToken()));

            long len = a + b;
            //만약 길이가 4 미만이라면 나올 수 있는 게없음
            if (len < 4) {
                sb.append("NO").append("\n");
            }

            //만약 len이 짝수라면 골드바흐의 추측에 의해 무조건 두 소수의 합으로 나타낼 수 있음
            else if (len % 2 == 0) {
                sb.append("YES").append("\n");
            }

            //그외의 값은 판단해야함
            else {
                //골드바흐의 추측에 의해 홀수들만 남게 됨
                //홀수는 짝수 + 홀수 인데 짝수인 소수는 2밖에 없음
                //그렇기 때문에 합에서 짝수 소수인 2를 빼고 남은 숫자가 소수인지만 판단하면 됨
                if (checkPrime(len - 2)) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);

    }

    private static boolean checkPrime(long num) {
        //만약 지금 선언한 배열(2000000)보다 작으면 배열에서 찾아서 소수인지 판단해서 return
        if (num < primeNum.length) return !primeNum[(int) num];

        //선언한 배열보다 크다면
        for (Integer prime : primes) {
            //2000000이하의 소수로 나눠떨어지면 그건 소수가 아님
            if (num % prime == 0) return false;
        }
        return true;
    }

    private static void getPrimeNum() {
        for (int i = 2; i < primeNum.length; i++) {
            if (primeNum[i]) continue;
            primes.add(i);
            for (int j = i * 2; j < primeNum.length; j += i) {
                primeNum[j] = true;
            }
        }
    }
}
