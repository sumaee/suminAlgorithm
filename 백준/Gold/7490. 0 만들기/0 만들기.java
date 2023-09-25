import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static String[] choose;
    static String[] oper = {" ", "+", "-"};
    static List<Integer> num;
    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            n = Integer.parseInt(br.readLine());

            num = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                num.add(i);
            }

            choose = new String[n - 1];
            findAnswer(0);
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static void findAnswer(int idx) {
        if (idx == n - 1) {
            //계산
            if (cal(new ArrayList<>(num), new ArrayList<>(Arrays.asList(choose)))) {
                for (int i = 1; i <= n; i++) {
                    if (i != n) {
                        sb.append(i).append(choose[i - 1]);
                    } else {
                        sb.append(i).append("\n");
                    }
                }
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            choose[idx] = oper[i];
            findAnswer(idx + 1);
        }
    }

    private static boolean cal(ArrayList<Integer> num, ArrayList<String> choose) {
        //빈칸이 있다면 숫자들 부터 합쳐주기
        for (int i = 0; i < choose.size(); i++) {
            if (choose.get(i).equals(" ")) {
                num.add(i, num.remove(i) * 10 + num.remove(i));
                choose.remove(i--);
            }
        }
        //앞에서부터 +-연산 시작
        int answer = num.get(0);
        for (int i = 0; i < choose.size(); i++) {
            if (choose.get(i).equals("+")) {
                answer += num.get(i + 1);
            } else {
                answer -= num.get(i + 1);
            }
        }

        return answer == 0;

    }
}
