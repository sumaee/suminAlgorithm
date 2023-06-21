import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) continue;
            if (checkNum(num)) answer++;
        }
        System.out.println(answer);
    }

    private static boolean checkNum(int num) {

        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
