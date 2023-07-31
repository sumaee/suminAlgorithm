import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        //숫자만 들어오는 문제를 골라주는 정규식
        String num = "^[\\d]*$";

        int n = Integer.parseInt(st.nextToken()); // 포켓몬 수
        int m = Integer.parseInt(st.nextToken()); // 문제 수

        Map<Integer, String> pokemonNum = new HashMap<>();
        Map<String, Integer> pokemonName = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            pokemonNum.put(i, name);
            pokemonName.put(name, i);
        }

        for (int i = 0; i < m; i++) {
            String problem = br.readLine();

            //숫자로 들어온다면 value 값 찾기
            if (Pattern.matches(num, problem)) {
                sb.append(pokemonNum.get(Integer.parseInt(problem)));
            } else {
                sb.append(pokemonName.get(problem));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
