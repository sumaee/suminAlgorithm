import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Integer, Integer> factory;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        factory = new HashMap<>();
        tree = new long[n + 1];

        //각 번호와 인덱스를 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            factory.put(Integer.parseInt(st.nextToken()), i);
        }

        //다음 줄을 돌면서 구간합 갱신
        long answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            //인덱스 가져오기
            int idx = factory.get(Integer.parseInt(st.nextToken()));
            //해당 인덱스 까지의 구간합 가져오기
            //연결지은 부분의 앞부분의 구간합을 구하면 됨
            answer += (sum(n) - sum(idx));
            update(idx);
        }

        System.out.println(answer);
    }

    private static long sum(int idx) {
        long sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    private static void update(int idx) {
        while (idx < tree.length) {
            tree[idx] += 1;
            idx += (idx & -idx);
        }
    }
}
