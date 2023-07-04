import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int a, b, c, d, answer;
    static Set<Bottle> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken()); // a의 물통 용량
        b = Integer.parseInt(st.nextToken()); // b의 물통 용량
        c = Integer.parseInt(st.nextToken()); // a에 담을 양
        d = Integer.parseInt(st.nextToken()); // b에 담을 양
        visited = new HashSet<>();
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Bottle> que = new LinkedList<>();
        que.offer(new Bottle(0, 0));

        while (!que.isEmpty()) {
            Bottle curr = que.poll();

            //만약 물을 다 채웠으면 끝
            if (curr.aBottle == c && curr.bBottle == d) {
                answer = curr.cnt;
                return;
            }
            //아니라면 모든 경우의 수 확인
            // 0 : a에 물을 채우는 경우
            // 1 : b에 물을 채우는 경우
            // 2 : a의 물을 버리는 경우
            // 3 : b의 물을 버리는 경우
            // 4 : a의 물을 b에 옮기는 경우
            // 5 : b의 물을 a에 옮기는 경우
            for (int i = 0; i < 6; i++) {
                int nxtA = curr.aBottle;
                int nxtB = curr.bBottle;

                //비어 있을 때 물을 버리거나 옮기는건 소용없으므로 다음으로
                if (nxtA == 0 && (i == 2 || i == 4)) continue;
                if (nxtB == 0 && (i == 3 || i == 5)) continue;

                //이미 꽉차있는데 채우는 거는 소용없으므로 패스
                if (nxtA == a && i == 0) continue;
                if (nxtB == b && i == 1) continue;

                if (i == 0) nxtA = a;
                else if (i == 1) nxtB = b;
                else if (i == 2) nxtA = 0;
                else if (i == 3) nxtB = 0;
                else if (i == 4) {
                    //b에 a의 물을 다 담을 수 있다면
                    if (nxtA + nxtB < b) {
                        nxtB += nxtA;
                        nxtA = 0;
                    }
                    //다 못담는다면
                    else {
                        int left = b - nxtB; // b의 남은 공간
                        nxtA -= left;
                        nxtB = b;
                    }
                } else {
                    //a에 b의 물을 다 담을 수 있다면
                    if (nxtA + nxtB < a) {
                        nxtA += nxtB;
                        nxtB = 0;
                    }
                    //다 못담는다면
                    else {
                        int left = a - nxtA;
                        nxtB -= left;
                        nxtA = a;
                    }
                }

                //이미 이 양을 담은 적이 없다면 큐에 넣기
                if (!visited.contains(new Bottle(nxtA, nxtB))) {
                    visited.add(new Bottle(nxtA, nxtB));
                    que.offer(new Bottle(nxtA, nxtB, curr.cnt + 1));
                }
            }
        }

        //while문이 끝났는데도 답을 못내면 답이 없는 것이므로 -1
        answer = -1;
    }

    static class Bottle {
        int aBottle, bBottle, cnt;

        public Bottle(int aBottle, int b) {
            this.aBottle = aBottle;
            this.bBottle = b;
        }

        public Bottle(int aBottle, int b, int cnt) {
            this.aBottle = aBottle;
            this.bBottle = b;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bottle bottle = (Bottle) o;
            return aBottle == bottle.aBottle && bBottle == bottle.bBottle;
        }

        @Override
        public int hashCode() {
            return Objects.hash(aBottle, bBottle);
        }
    }

}
