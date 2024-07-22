import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] todayArr = new int[3];
        int[] dDayArr = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            todayArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            dDayArr[i] = Integer.parseInt(st.nextToken());
        }

        LocalDate today = LocalDate.of(todayArr[0], todayArr[1], todayArr[2]);
        LocalDate dDay = LocalDate.of(dDayArr[0], dDayArr[1], dDayArr[2]);

        if (today.isBefore(dDay.minusYears(1000)) || today.isEqual(dDay.minusYears(1000))) {
            System.out.println("gg");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("D-");
            sb.append(ChronoUnit.DAYS.between(today, dDay));
            System.out.println(sb);
        }
    }
}