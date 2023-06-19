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
        List<Pair2> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Pair2(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        list.sort((o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y);

        for (Pair2 pair : list) {
            System.out.println(pair.x + " " + pair.y);
        }
    }
}

class Pair2 {
    int x, y;

    public Pair2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}