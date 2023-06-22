import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        //최대공약수 구하기
        int gcd = 0;
        if (a > b) gcd = getGcd(a, b);
        else gcd = getGcd(b, a);

        //최소 공배수 구하기
        int lcm = a * b / gcd;

        System.out.println(gcd + "\n" + lcm);
    }

    private static int getGcd(int a, int b) {
        return b != 0 ? getGcd(b, a % b) : a;
    }
}
