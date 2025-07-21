import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int sum = 0;

        for (int i = 1; i < n; i++) {
            sum = i;
            int temp = i;
            while (temp > 0) {
                sum += (temp % 10);
                temp /= 10;
            }

            if (sum == n) {
                sum = i;
                System.out.println(sum);
                return;
            }
        }

        System.out.println(0);
    }
}
