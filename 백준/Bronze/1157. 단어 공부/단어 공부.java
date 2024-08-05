import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().toLowerCase();

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        boolean flag = true;
        int cnt = Integer.MIN_VALUE;
        char answer = 0;
        for (char key : map.keySet()) {
            if (cnt < map.get(key)) {
                cnt = Math.max(map.get(key), cnt);
                answer = key;
                flag = true;
            } else if (cnt == map.get(key)) {
                flag = false;
            }
        }

        System.out.println(flag ? String.valueOf(answer).toUpperCase() : '?');
    }
}