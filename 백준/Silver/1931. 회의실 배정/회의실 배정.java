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
        List<Meet> meets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meets.add(new Meet(start, end));
        }

        meets.sort((o1, o2) -> o1.end == o2.end ? o1.start - o2.start : o1.end - o2.end);
        int answer = 0;
        int time = 0;
        for (int i = 0; i < meets.size(); i++) {
            Meet meet = meets.get(i);

            if (meet.start >= time) {
                time = meet.end;
                answer++;
            }
        }
        System.out.println(answer);
    }
}

class Meet {
    int start, end;

    public Meet(int start, int end) {
        this.start = start;
        this.end = end;
    }
}