import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        long monkey = Long.parseLong(st.nextToken());
        long dog = Long.parseLong(st.nextToken());

        if (monkey == dog) {
            System.out.println(0);
            return;
        }

        long diff = dog - monkey;
        long sqrt = (long) Math.sqrt(diff);

        if (sqrt * sqrt == diff) {
            System.out.println(2 * sqrt - 1);
        } else if (sqrt * sqrt < diff && sqrt * (sqrt + 1) >= diff) {
            System.out.println(2 * sqrt);
        } else if (sqrt * (sqrt + 1) < diff && (sqrt + 1) * (sqrt + 1) > diff) {
            System.out.println(2 * sqrt + 1);
        }
    }
}
