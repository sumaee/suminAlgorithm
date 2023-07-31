import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, r, c, idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        //2^n * 2^n 배열 먼저 만들기
        int size = (int) Math.pow(2, n);
        idx = 0;
        find(0, 0, size);
        System.out.println(idx);
    }

    private static void find(int row, int col, int size) {
        //각 위치가 어느 사분면에 속하는지 찾아서 재귀 돌려주기
        if (size == 1) {
            return;
        }

        int nxtSize = size / 2;
        //찾고자 하는 위치가 만약 왼쪽 위에 존재한다면
        if (r < nxtSize + row && c < nxtSize + col) {
            find(row, col, nxtSize);
        }

        //찾고자 하는 위치가 만약 오른쪽 위에 존재한다면
        if (r < nxtSize + row && c >= nxtSize + col) {
            idx += (size * size) / 4;
            find(row, col + nxtSize, nxtSize);
        }

        //찾고자 하는 위치가 만약 왼쪽 아래에 존재한다면
        if (r >= nxtSize + row && c < nxtSize + col) {
            idx += (size * size) / 4 * 2;
            find(row + nxtSize, col, nxtSize);
        }

        //찾고자 하는 위치가 만약 오른쪽 아래에 존재한다면
        if (r >= nxtSize + row && c >= nxtSize + col) {
            idx += (size * size) / 4 * 3;
            find(row + nxtSize, col + nxtSize, nxtSize);
        }
    }
}
