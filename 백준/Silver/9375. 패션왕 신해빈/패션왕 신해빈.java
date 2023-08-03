import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            int n = Integer.parseInt(br.readLine());

            //각 의상의 종류에 대해 개수를 담을 map
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String clothes = st.nextToken();
                String kind = st.nextToken();

                map.put(kind, map.getOrDefault(kind, 0) + 1);
            }

            //입을 수 있는 경우의 수는 조합을 생각해보면됨
            //한가지 종류의 수에 대해 입을 수 있는 경우의 수는 (옷 종류 개수)+1 임
            int answer = 1;
            for (String key : map.keySet()) {
                answer *= (map.get(key) + 1);
            }

            //알몸으로 다니는 경우 1가지를 빼주면 됨
            sb.append(answer - 1).append("\n");
        }
        System.out.println(sb);
    }
}
