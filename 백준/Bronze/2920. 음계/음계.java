import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int curr = start;
        for (int i = 0; i < 7; i++) {
            int nxt = Integer.parseInt(st.nextToken());

            if (start == 8) {
                if (nxt != (curr - 1)) {
                    System.out.println("mixed");
                    return;
                }

                curr--;
            } else {
                if (nxt != (curr + 1)) {
                    System.out.println("mixed");
                    return;
                }

                curr++;
            }
        }

        if (start == 8) {
            System.out.println("descending");
        } else {
            System.out.println("ascending");
        }
    }
}
