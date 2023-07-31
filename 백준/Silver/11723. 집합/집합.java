import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int result = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = 0;
            if (!command.equals("all") && !command.equals("empty")) {
                num = Integer.parseInt(st.nextToken()) - 1;
            }

            if (command.equals("add")) {
                result |= (1 << num);
            } else if (command.equals("remove")) {
                result &= ~(1 << num);
            } else if (command.equals("check")) {
                if ((result & (1 << num)) == (1 << num)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else if (command.equals("toggle")) {
                result ^= (1 << num);
            } else if (command.equals("all")) {
                result = (1 << 20) - 1;
            } else {
                result = 0;
            }
        }

        System.out.println(sb);
    }
}
