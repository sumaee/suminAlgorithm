import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            if (list.get(0) == 0 && list.get(1) == 0 && list.get(2) == 0) {
                break;
            }

            list.sort(Comparator.comparingInt(o -> o));

            if (Math.pow(list.get(0), 2) + Math.pow(list.get(1), 2) == Math.pow(list.get(2), 2)) {
                sb.append("right").append("\n");
            } else {
                sb.append("wrong").append("\n");
            }
        }

        System.out.println(sb);
    }
}
