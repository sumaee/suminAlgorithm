import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //n이 10보다 작으면 그 자체로 정답
        if (n <= 10) {
            System.out.println(n);
        }
        //n이 1022라면 감소하는 수가 9876543210이 나오므로 제일 큰 수임->따라서 1022보다 크다면 없음
        else if (n > 1022) {
            System.out.println(-1);
        } else {
            list = new ArrayList<>();
            //i로 시작하는 숫자중 감소하는 숫자 찾아서 넣기
            for (int i = 0; i < 10; i++) {
                addNum(i, 1);
            }

            Collections.sort(list);
            System.out.println(list.get(n));
        }
    }

    private static void addNum(long num, int idx) {
        if (idx > 10) {
            return;
        }

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            //다음 자릿수 확인
            addNum((num * 10) + i, idx + 1);
        }
    }
}
