import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] num, operator;
    static List<Integer> list;
    static boolean[] visited;
    static int max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int cnt = Integer.parseInt(st.nextToken());

            while (cnt != 0) {
                list.add(i);
                cnt--;
            }
        }
        operator = new int[list.size()];
        visited = new boolean[operator.length];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        permutation(0, operator.length);
        System.out.println(max + "\n" + min);

    }

    private static void permutation(int idx, int limit) {
        if (idx == limit) {
            //계산
            int answer = calculate(limit);
            max = Math.max(answer, max);
            min = Math.min(answer, min);
            return;
        }

        for (int i = 0; i < limit; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            operator[idx] = list.get(i);
            permutation(idx + 1, limit);
            visited[i] = false;
        }
    }

    private static int calculate(int limit) {
        int result = num[0];

        for (int i = 0; i < limit; i++) {
            int operate = operator[i];

            if (operate == 0) {
                result += num[i + 1];
            } else if (operate == 1) {
                result -= num[i + 1];
            } else if (operate == 2) {
                result *= num[i + 1];
            } else {
                result /= num[i + 1];
            }
        }

        return result;
    }
}
