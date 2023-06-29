import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] gates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        gates = new int[g + 1];
        for (int i = 0; i <= g; i++) {
            gates[i] = i;
        }

        int p = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int i = 0; i < p; i++) {
            int landing = Integer.parseInt(br.readLine());
            //착륙할 수 있는 게이트 찾기
            int gate = find(landing);

            //게이트가 0번이라면 더이상 착륙할 수 없으므로 끝
            if (gate == 0) {
                break;
            }

            //착륙시킬수 있다면 해당 게이트에서 다음 게이트를 가리키게끔 하기
            answer++;
            //순서 중요
            union(gate - 1, gate);
        }

        System.out.println(answer);
    }

    private static void union(int x, int y) {
        gates[find(y)] = find(x);
    }

    private static int find(int x) {
        if (x == gates[x]) {
            return x;
        }

        return gates[x] = find(gates[x]);
    }
}
