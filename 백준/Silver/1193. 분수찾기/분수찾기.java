import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());

        int sum = 0;
        int idx = 0;
        for (int i = 1; i <= 10000000; i++) {
            sum += i;
            if (sum >= num) {
                idx = i;
                break;
            }
        }

        if (idx % 2 == 0) {
            sb.append(idx - sum + num).append("/").append(sum - num + 1);
        } else {
            sb.append(sum - num + 1).append("/").append(idx - sum + num);
        }

        System.out.println(sb);
    }
}