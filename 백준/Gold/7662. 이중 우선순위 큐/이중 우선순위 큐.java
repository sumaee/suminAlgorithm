import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            TreeMap<Long, Long> tm = new TreeMap<>();

            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                Long num = Long.parseLong(st.nextToken());
                //삽입 연산이라면
                if (command.equals("I")) {
                    tm.put(num, tm.getOrDefault(num, 0L) + 1);
                }
                //삭제 연산이라면
                else if (command.equals("D") && !tm.isEmpty()) {
                    Long removeNum;
                    //최댓값을 삭제하는 연산이라면 그냥 삭제
                    if (num == 1) {
                        removeNum = tm.lastKey();
                    }
                    //최솟값을 삭제하는 연산이라면
                    else {
                        removeNum = tm.firstKey();
                    }

                    //개수를 저장해줬으므로 빼고 난 후의 값이 0이라면 제거
                    if (tm.get(removeNum) - 1 == 0) {
                        tm.remove(removeNum);
                    } else {
                        tm.put(removeNum, tm.get(removeNum) - 1);
                    }
                }
            }

            if (tm.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
