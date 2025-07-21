import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int m = 1234567891;
        long pow = (long) Math.pow(31, 0);

        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += ((str.charAt(i) - 96) * pow % m);
            pow = (pow * 31) % m;
        }

        System.out.println(sum % m);
    }
}
