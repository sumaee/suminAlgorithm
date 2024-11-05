import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] count;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        count = new int[1002];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            count[num]++;
        }

        int idx = 0;
        while (n > 0) {
            if (count[idx] == 0) {
                idx++;
                continue;
            }

            if (count[idx + 1] != 0) {
                boolean flag = false;
                for (int i = idx + 2; i < 1001; i++) {
                    if (count[i] != 0) {
                        append(idx);
                        sb.append(i).append(" ");
                        count[i]--;
                        n--;
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    append(idx + 1);
                    append(idx);
                }
            } else {
                append(idx);
            }
            idx++;
        }
        System.out.println(sb);
    }

    private static void append(int num) {
        while (count[num] != 0) {
            sb.append(num).append(" ");
            count[num]--;
            n--;
        }
    }
}