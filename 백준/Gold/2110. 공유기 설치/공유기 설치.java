import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        house = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, house[i]);
        }
        Arrays.sort(house);
        
        int min = 1;
        int answer = 0;
        while (min <= max) {
            //두 공유기 사이의 최대 거리
            int mid = (min + max) / 2;
            int count = checkSetting(mid);
            
            //설정했던 최대 거리로 했을 때 공유기가 c보다 작다면
            //거리 간격을 좁혀야함
            if (count < c) {
                max = mid - 1;
            }

            //설치된 공유기가 c보다 많다면 거리 간격을 늘려도됨
            else {
                min = mid + 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }

    private static int checkSetting(int mid) {
        int count = 1;
        //첫번째 집은 무조건 설치
        int setting = house[0];

        for (int i = 1; i < n; i++) {
            //mid만큼 떨어져 있다면 해당 집에 공유기 세팅을 하고 그 집부터 다음 설치할 집까지 거리 확인
            if (house[i] - setting >= mid) {
                count++;
                setting = house[i];
            }
        }

        return count;
    }
}
