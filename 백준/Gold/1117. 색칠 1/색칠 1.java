import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long w = Long.parseLong(st.nextToken());
        long h = Long.parseLong(st.nextToken());
        long f = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        long area = (x2 - x1) * (y2 - y1) * (c + 1);
        long temp = 0;

        //왼쪽크기 <=오른쪽 크기
        if (f <= w / 2) {
            //왼쪽 영향을 안받을 떄
            if (f <= x1) {
                temp = w * h - area;
            }
            //왼쪽 영향을 받을 때
            else {
                temp = w * h - (area + (Math.min(f, x2) - x1) * (y2 - y1) * (c + 1));
            }
        }
        //왼쪽크기 > 오른쪽 크기
        else {
            //오른쪽의 영향을 안받을떄
            if (w <= x1 + f) {
                temp = w * h - area;
            }

            //오른쪽의 영향을 받을 때
            else {
                temp = w * h - (area + (Math.min(w, f + x2) - (f + x1)) * (y2 - y1) * (c + 1));
            }
        }

        System.out.println(temp);
    }
}
