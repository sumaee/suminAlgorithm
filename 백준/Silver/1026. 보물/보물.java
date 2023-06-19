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

        int n = Integer.parseInt(br.readLine());
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }
        a.sort((o1, o2) -> o1 - o2);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b.add(Integer.parseInt(st.nextToken()));
        }
        b.sort((o1, o2) -> o2 - o1);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a.get(i) * b.get(i);

        }

        System.out.println(sum);
    }
}
