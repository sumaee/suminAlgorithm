import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] friends, cnt;
    static int f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            Map<String, Integer> info = new HashMap<>();
            f = Integer.parseInt(br.readLine());

            friends = new int[f * 2];
            cnt = new int[f * 2];
            for (int i = 0; i < f * 2; i++) {
                friends[i] = i;
                cnt[i] = 1;
            }

            int idx = 0;
            for (int i = 0; i < f; i++) {
                st = new StringTokenizer(br.readLine());

                String friendA = st.nextToken();
                String friendB = st.nextToken();

                //만약 map에 정보가 없다면 저장
                if (!info.containsKey(friendA)) {
                    info.put(friendA, idx++);
                }
                if (!info.containsKey(friendB)) {
                    info.put(friendB, idx++);
                }

                //친구 관계 합치고 친구 수 세기
                union(info.get(friendA), info.get(friendB));
                sb.append(cnt[find(info.get(friendA))]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        //둘의 부모가 다르면 친구 관계 만들기
        if (x != y) {
            friends[y] = x;

            //친구 수 늘리기
            cnt[x] += cnt[y];
            cnt[y] = cnt[x];
        }
    }

    private static int find(int num) {
        if (friends[num] == num) {
            return num;
        }

        return friends[num] = find(friends[num]);
    }

}
