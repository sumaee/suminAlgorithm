import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int[] students;
    static Map<Integer, Set<Integer>> studentInfo;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        students = new int[n * n];
        studentInfo = new HashMap<>();
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());

            students[i] = Integer.parseInt(st.nextToken());
            studentInfo.put(students[i], new HashSet<>());

            //해당 학생이 좋아하는 4명 넣기
            for (int j = 0; j < 4; j++) {
                studentInfo.get(students[i]).add(Integer.parseInt(st.nextToken()));
            }
        }


        //학생을 돌면서 자리 배치
        for (int i = 0; i < students.length; i++) {
            Seat seat = findSeat(students[i]);
            map[seat.row][seat.col] = students[i];
        }

        //만족도 구하기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = getLike(i, j, map[i][j]);

                if (cnt != 0) {
                    answer += (int) Math.pow(10, cnt - 1);
                }
            }
        }
        System.out.println(answer);

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    private static Seat findSeat(int studentIdx) {
        Seat seat = null;

        //지도를 돌면서 조건에 맞는 자리 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //누가 앉아 있으면 패스
                if (map[i][j] != 0) continue;

                //현재 자리 정보 저장
                Seat curr = new Seat(i, j, getEmpty(i, j), getLike(i, j, studentIdx));

                //현재 seat이 빈칸이라면 curr로 갱신
                if (seat == null) {
                    seat = curr;
                    continue;
                }

                //빈칸이 아니라면 조건에 따라 현재 seat 변경
                if (seat.compareTo(curr) > 0) {
                    seat = curr;
                }
            }
        }

        return seat;
    }

    private static int getEmpty(int row, int col) {
        //사방을 탐색해서 빈칸 확인
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //범위 벗어나거나 해당 자리에 누가 앉아있다면 패스
            if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || map[drow][dcol] != 0) continue;

            cnt++;
        }
        return cnt;
    }

    private static int getLike(int row, int col, int studentIdx) {
        //사방 탐색해서 사람이 앉아있을 때 자신이 좋아하는 사람이 있는지 확인
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int drow = row + dr[i];
            int dcol = col + dc[i];

            //범위 벗어나거나 해당 자리에 아무도 없다면 패스
            if (drow < 0 || dcol < 0 || drow >= n || dcol >= n || map[drow][dcol] == 0) continue;

            //학생이 좋아하는 사람인지 확인
            if (studentInfo.get(studentIdx).contains(map[drow][dcol])) {
                cnt++;
            }
        }
        return cnt;
    }

    static class Seat implements Comparable<Seat> {
        int row, col, empty, like;

        public Seat(int row, int col, int empty, int like) {
            this.row = row;
            this.col = col;
            this.empty = empty;
            this.like = like;
        }

        @Override
        public int compareTo(Seat o) {
            //좋아하는 학생의 수로 비교
            if (like != o.like) return o.like - like;

            //비어있는 칸의 수로 비교
            if (empty != o.empty) return o.empty - empty;

            //행으로 비교
            if (row != o.row) return o.row - row;

            //열로 비교
            return o.col - col;
        }
    }
}