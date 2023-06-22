import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int[][] city;
    static List<Location> chicken;
    static List<Location> home;
    static List<Location> list;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        chicken = new ArrayList<>();
        home = new ArrayList<>();
        list = new ArrayList<>();

        int n = Integer.parseInt(st.nextToken()); //도시 크기
        m = Integer.parseInt(st.nextToken()); // 고를 치킨집의 개수

        //도시 채우면서 치킨집과 집 위치 저장
        city = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int locate = Integer.parseInt(st.nextToken());

                if (locate == 1) home.add(new Location(i, j));
                else if (locate == 2) chicken.add(new Location(i, j));
            }
        }

        min = Integer.MAX_VALUE;
        findChicken(0, 0);
        System.out.println(min);

    }

    private static void findChicken(int idx, int cnt) {
        //m개의 치킨집을 골랐을 때 확인
        if (cnt == m) {
            findMin();
            return;
        }

        //m개 치킨집 고르기
        for (int i = idx; i < chicken.size(); i++) {
            list.add(chicken.get(i));
            findChicken(i + 1, cnt + 1);
            list.remove(chicken.get(i));
        }
    }

    private static void findMin() {
        //각 집을 기준으로 치킨집들의 최솟값 구하기
        int sum = 0;
        for (int i = 0; i < home.size(); i++) {
            int hr = home.get(i).row;
            int hc = home.get(i).col;
            int dis = Integer.MAX_VALUE;

            for (int j = 0; j < list.size(); j++) {
                int cr = list.get(j).row;
                int cc = list.get(j).col;

                dis = Math.min(dis, Math.abs(hr - cr) + Math.abs(hc - cc));
            }
            sum += dis;
        }
        min = Math.min(sum, min);
    }
}

class Location {
    int row, col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
