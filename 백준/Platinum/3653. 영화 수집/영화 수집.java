import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] locate, movie;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());// 영화의 수
            int m = Integer.parseInt(st.nextToken()); // 보고자하는 영화의 수

            locate = new int[n + 1];
            movie = new int[n + m + 1];

            //영화를 맨 밑에부터 깔아야하므로 m+i번째에 깔기
            for (int i = 1; i <= n; i++) {
                locate[i] = i + m;
                //locate[i]부터 하나씩 증가
                update(locate[i], 1);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());

                //찾고자 하는 영와 위에 쌓여있는 개수 구하기
                sb.append(sum(locate[num] - 1)).append(" ");

                //본영화 위로 뺏을 때 해당 영화의 밑에 깔린 애들 일단 하나씩 빼주기
                update(locate[num], -1);
                //영화 위로 올리기
                locate[num] = m - i;
                //영화를 위로 올렸으니까 모든 영화들 1개씩 증가
                update(locate[num], 1);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int sum(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += movie[idx];

            idx -= idx & -idx;
        }
        return sum;
    }

    private static void update(int idx, int val) {
        while (idx < movie.length) {
            movie[idx] += val;
            idx += idx & -idx;
        }
    }
}
