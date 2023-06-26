import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static boolean[] visited;
    static int[] nums;
    static int[] way;

    static List<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        visited = new boolean[10];
        nums = new int[10];
        //숫자 채우기
        for (int i = 0; i <= 9; i++) {
            nums[i] = i;
        }

        way = new int[k];
        //부등호 채우기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            String str = st.nextToken();

            //작다 부등호면 -1, 크다 부등호면 1
            if (str.equals("<")) {
                way[i] = -1;
            } else {
                way[i] = 1;
            }
        }

        answer = new ArrayList<>();
        findNums("", 0);

        Collections.sort(answer);

        System.out.println(answer.get(answer.size() - 1));
        System.out.println(answer.get(0));

    }

    private static void findNums(String num, int cnt) {
        if (cnt == k + 1) {
            answer.add(num);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i]) continue;

            if (cnt == 0 || checkWay(num.charAt(cnt - 1), i, way[cnt - 1])) {
                visited[i] = true;
                findNums(num + i, cnt + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean checkWay(char front, int n, int way) {
        if (way == -1) {
            if (front - '0' > n) {
                return false;
            }
        } else {
            if (front - '0' < n) {
                return false;
            }
        }

        return true;
    }
}
