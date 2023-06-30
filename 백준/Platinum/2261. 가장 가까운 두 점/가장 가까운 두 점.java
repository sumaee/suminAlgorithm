import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Locate> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Locate(x, y));
        }

        //x 기준으로 오름차순 정리
        list.sort((o1, o2) -> o1.x - o2.x);

        //리스트에 들어가있는 것의 idx를 기준으로 분할 정복 
        System.out.println(getDivide(0, n - 1));
    }

    //거리의 제곱을 구하는 함수
    private static int getDis(Locate a, Locate b) {
        return (int) (Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    //가장 가까운 거리 찾기
    private static int findDis(int left, int right) {
        int result = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j <= right; j++) {
                int dis = getDis(list.get(i), list.get(j));
                result = Math.min(result, dis);
            }
        }
        return result;
    }

    //분할 정복으로 점들 확인
    private static int getDivide(int left, int right) {
        int num = right - left + 1;

        //만약 점이 3개 이하이면 계산
        if (num <= 3) {
            return findDis(left, right);
        }

        int mid = (left + right) / 2;

        int x = getDivide(left, mid);
        int y = getDivide(mid + 1, right);
        int answer = Math.min(x, y);

        //기준점을 중심으로 왼쪽 오른쪽 거리 구하기
        List<Locate> locates = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            //x의 범위가 Result 범위 내에 있는 점들을 보기 위한 작업
            int dis = list.get(i).x - list.get(mid).x;
            //해당 거리가 현재 제일 짧은 거리보다 더 짧다면 확인해야하므로 list 에 넣기
            if (dis * dis < answer) {
                locates.add(list.get(i));
            }
        }

        //y 축 기준으로 다시 확인하기
        locates.sort((o1, o2) -> o1.y - o2.y);
        for (int i = 0; i < locates.size() - 1; i++) {
            for (int j = i + 1; j < locates.size(); j++) {
                int dis = locates.get(i).y - locates.get(j).y;
                //y축에 있는 거리가 더 짧으면 거리 확인해서 갱신
                if (dis * dis < answer) {
                    int d = getDis(locates.get(i), locates.get(j));
                    answer = Math.min(d, answer);
                } else {
                    break;
                }
            }
        }
        return answer;
    }
}

class Locate {
    int x, y;

    public Locate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
