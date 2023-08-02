import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        sb.append("IO".repeat(Math.max(0, n)));
        sb.append("I");
        String target = sb.toString();

        int s = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int answer = 0;
        for (int i = 0; i <= s - target.length(); i++) {
            if (str.startsWith(target, i)) answer++;
        }
        System.out.println(answer);
    }

}
