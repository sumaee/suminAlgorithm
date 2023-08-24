import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Line[] lines = new Line[n];
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
            parents[i] = i;
        }

        //만나는지 확인해서 union
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isLineCross(lines[i], lines[j])) {
                    int parentI = find(i);
                    int parentJ = find(j);

                    //부모가 다른데 만나는 것이므로 부모 합치기
                    if (parentI != parentJ) {
                        parents[parentI] = parentJ;
                    }
                }
            }
        }

        //정답 구하기
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 1;

        for (int i = 0; i < n; i++) {
            int parent = find(parents[i]);

            map.put(parent, map.getOrDefault(parent, 0) + 1);
            cnt = Math.max(cnt, map.get(parent));
        }

        System.out.println(map.size());
        System.out.println(cnt);
    }

    private static int find(int idx) {
        if (parents[idx] == idx) return idx;

        return parents[idx] = find(parents[idx]);
    }

    private static boolean isLineCross(Line l1, Line l2) {
        //첫번째를 기준으로 두번째가 교차하는지 확인
        long line1 = checkCross(l1.p1, l1.p2, l2.p1) * checkCross(l1.p1, l1.p2, l2.p2);

        //두번째를 기준으로 첫번째가 교차하는지 확인
        long line2 = checkCross(l2.p1, l2.p2, l1.p1) * checkCross(l2.p1, l2.p2, l1.p2);

        //두 선이 일직선 상에 있는 경우
        if (line1 == 0 && line2 == 0) {
            //line1의 점의 크기 순서를 p1<p2로 맞춤
            if (checkSize(l1.p2, l1.p1)) {
                Point temp = l1.p1;
                l1.p1 = l1.p2;
                l1.p2 = temp;
            }

            //line2의 점의 크기 순서를 p1<p2로 맞출
            if (checkSize(l2.p2, l2.p1)) {
                Point temp = l2.p1;
                l2.p1 = l2.p2;
                l2.p2 = temp;
            }

            return checkSize(l2.p1, l1.p2) && checkSize(l1.p1, l2.p2);
        }

        return (line1 <= 0) && (line2 <= 0);
    }

    private static boolean checkSize(Point p1, Point p2) {
        if (p1.x == p2.x) {
            return p1.y <= p2.y;
        }
        return p1.x <= p2.x;
    }

    private static long checkCross(Point p1, Point p2, Point p3) {
        long check = (long) (p2.x - p1.x) * (p3.y - p1.y) - (long) (p3.x - p1.x) * (p2.y - p1.y);

        if (check > 0) {
            return 1;
        } else if (check < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


    }

    static class Line {
        Point p1, p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}

