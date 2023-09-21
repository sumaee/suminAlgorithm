import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] peoples = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            peoples[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long answer = 0;
        for (int people : peoples) {
            //총감독관을 무조건 넣기
            answer++;

            //총감독관이 감독하고 난 후의 사람 수 구하기
            people -= b;
            //사람이 0명 이하가 되면 부감독이 필요없음
            if (people <= 0) {
                continue;
            }

            //부감독이 감독할 수 있는 학생수가 딱 나눠떨어지면
            if (people % c == 0) {
                answer += people / c;
            } else {
                answer += people / c + 1;
            }

        }
        System.out.println(answer);
    }
}
