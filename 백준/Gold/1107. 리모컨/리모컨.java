import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 채널
        int m = Integer.parseInt(br.readLine()); // 고장난 버튼 수

        //채널이 100번 그대로라면 움직일 필요가 없음
        if (n == 100) {
            System.out.println(0);
            return;
        }

        //그 외 라면
        Set<Integer> brokenButton = new HashSet<>();
        if (m != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                brokenButton.add(Integer.parseInt(st.nextToken()));
            }
        }

        //우선 무식하게 눌러서 찾기
        int answer = Math.abs(n - 100);

        //0부터 999999까지 돌려서 가능한 경우를 찾음
        for (int i = 0; i <= 999999; i++) {
            String num = String.valueOf(i); // 누를 번호

            //누를 수있는 번호 찾기
            boolean check = true;
            for (int j = 0; j < num.length(); j++) {
                //누를 버튼 중에 고장난 번호를 포함하고 있다면
                if (brokenButton.contains(num.charAt(j) - '0')) {
                    check = false;
                    break;
                }
            }

            //누르려는 번호 중에 고장난게 없다면
            if (check) {
                int cnt = num.length() + Math.abs(n - i); // 해당 수를 누르고 + 목표 채널까지 +/- 누른 횟수
                answer = Math.min(answer, cnt);
            }
        }
        System.out.println(answer);
    }
}
