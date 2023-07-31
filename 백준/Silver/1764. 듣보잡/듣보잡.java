import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            if (set.contains(name)) {
                list.add(name);
            }
        }

        Collections.sort(list);
        sb.append(list.size()).append("\n");

        for (String name : list) {
            sb.append(name).append("\n");
        }

        System.out.println(sb);
    }
}
