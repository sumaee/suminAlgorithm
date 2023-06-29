import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        result = 0;
        hanoiTop(1, 2, 3, n);
        System.out.println(result);
        System.out.println(sb);
    }

    private static void hanoiTop(int start, int mid, int end, int cnt) {
        //이동할 블럭이 한개일 경우 이동하고 return
        if (cnt == 1) {
            result++;
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        //첫번째에서 경유지 판으로 옮기기
        hanoiTop(start, end, mid, cnt - 1);
        sb.append(start).append(" ").append(end).append("\n");
        result++;
        //경유지에 있는 판을 목적지 판으로 옮기기
        hanoiTop(mid, start, end, cnt - 1);
    }
}
