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
        List<Integer> cranes = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        List<Integer> boxes = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        //정렬
        cranes.sort((o1, o2) -> o2 - o1);
        boxes.sort((o1, o2) -> o2 - o1);

        //처음부터 옮길 수 없는 것이라면 -1
        if (boxes.get(0) > cranes.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        while (!boxes.isEmpty()) {
            int idx = 0;

            for (int i = 0; i < n; ) {
                if (idx == boxes.size()) {
                    break;
                }

                //현재 i번째 크레인으로 옮길 수 있다면 박스 제거
                if (cranes.get(i) >= boxes.get(idx)) {
                    boxes.remove(idx);
                    i++;
                }
                //현재 i번째 크레인으로 옮길 수 없다면 다음 박스 확인
                else {
                    idx++;
                }
            }

            //크레인을 모두 돌아서 옮길수 있게 해놨으므로
            time++;
        }
        System.out.println(time);
    }
}
