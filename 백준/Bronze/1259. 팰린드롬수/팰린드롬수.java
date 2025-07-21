import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        nxt:
        while (true) {
            String line = br.readLine();
            if (line.equals("0")) break;

            if (line.length() % 2 == 0) {
                int left = 0;
                int right = line.length() - 1;

                while (left <= right) {
                    if (line.charAt(left) != line.charAt(right)) {
                        sb.append("no").append("\n");
                        continue nxt;
                    }
                    left++;
                    right--;
                }
            } else {
                int mid = line.length() / 2;
                for (int i = 0; i < mid; i++) {
                    if (line.charAt(i) != line.charAt(line.length() - i - 1)) {
                        sb.append("no").append("\n");
                        continue nxt;
                    }
                }
            }

            sb.append("yes").append("\n");
        }
        System.out.println(sb);
    }
}
