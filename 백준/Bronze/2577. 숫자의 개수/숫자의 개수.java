import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = String.valueOf(Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()));

        int[] cnts = new int[10];
        for (int i = 0; i < num.length(); i++) {
            cnts[num.charAt(i) - '0']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int cnt : cnts) {
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
