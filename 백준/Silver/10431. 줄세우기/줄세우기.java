import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            sb.append(st.nextToken()).append(" ");

            int[] studentInfos = new int[20];
            for (int i = 0; i < 20; i++) {
                studentInfos[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> lines = new ArrayList<>();
            lines.add(studentInfos[0]);
            int moveCnt = 0;
            boolean flag = true;
            for (int i = 1; i < 20; i++) {
                int size = lines.size();
                for (int j = 0; j < size; j++) {
                    if (lines.get(j) > studentInfos[i]) {
                        moveCnt += (size - j);
                        lines.add(j, studentInfos[i]);
                        flag = true;
                        break;
                    }

                    flag = false;
                }

                if (!flag) {
                    lines.add(studentInfos[i]);
                }
            }

            sb.append(moveCnt).append("\n");
        }
        System.out.println(sb);
    }
}