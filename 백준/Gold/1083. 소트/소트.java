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

        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int s = Integer.parseInt(br.readLine());

        //이동횟수가 남아있다면 진행
        for (int i = 0; i < n && s > 0; i++) {
            int temp = 0; // 자리를 바꿀 숫자를 기록
            int idx = 0; // 자리를 바꿔야하는 숫자의 idx 기록

            //현재 i번째의 숫자부터 s만큼의 범위를 확인해서 제일 큰 숫자를 기록
            for (int j = i; j < n && j <= s + i; j++) {
                if (temp <= list.get(j)) {
                    temp = list.get(j);
                    idx = j;
                }
            }

            //자리바꾸기
            list.remove(idx);
            list.add(i, temp);

            //이동한 횟수 차감은 i번째에서 idx번째의 숫자까지의 거리
            s -= (idx - i);
        }

        for (int num : list) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
