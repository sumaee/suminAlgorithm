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

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Info> infos = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                infos.add(new Info(a, b));
            }
            infos.sort((o1, o2) -> o1.b == o2.b ? o1.a - o2.a : o1.b - o2.b);
            boolean[] check = new boolean[n + 1];
            int answer = 0;
            for (int i = 0; i < m; i++) {
                int a = infos.get(i).a;
                int b = infos.get(i).b;

                for (int j = a; j <= b; j++) {
                    //해당 책을 주지 않았다면 주고 끝
                    if (!check[j]) {
                        check[j] = true;
                        answer++;
                        break;
                    }
                }
            }
            System.out.println(answer);
        }

    }
}

class Info {
    int a, b;

    public Info(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
