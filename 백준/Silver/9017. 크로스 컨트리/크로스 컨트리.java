import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] infos = new int[n];
            Map<Integer, Integer> playerCnt = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int team = Integer.parseInt(st.nextToken());
                infos[i] = team;
                playerCnt.put(team, playerCnt.getOrDefault(team, 0) + 1);
            }


            int rank = 1;
            Map<Integer, Team> results = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (playerCnt.get(infos[i]) < 6) {
                    continue;
                }
                if (results.containsKey(infos[i]) && results.get(infos[i]).cnt < 4) {
                    Team result = results.get(infos[i]);
                    result.update(rank);
                } else if (!results.containsKey(infos[i])) {
                    results.put(infos[i], new Team(infos[i], 1, rank, 0));
                } else if (results.get(infos[i]).cnt == 4) {
                    results.get(infos[i]).addFifthPlayerScore(rank);
                } else {
                    results.get(infos[i]).setOrder();
                }

                rank++;
            }

            List<Team> answer = new ArrayList<>(results.values());

            answer.sort((o1, o2) -> {
                if (o1.scoreSum == o2.scoreSum) {
                    return o1.fifthPlayer - o2.fifthPlayer;
                }
                return o1.scoreSum - o2.scoreSum;
            });

            System.out.println(answer.get(0).idx);

        }
    }

    static class Team {
        int idx, cnt, scoreSum, fifthPlayer;

        public Team(int idx, int cnt, int scoreSum, int fifthPlayer) {
            this.idx = idx;
            this.cnt = cnt;
            this.scoreSum = scoreSum;
            this.fifthPlayer = fifthPlayer;
        }

        public void update(int score) {
            this.cnt++;
            this.scoreSum += score;
        }

        public void addFifthPlayerScore(int score) {
            this.cnt++;
            this.fifthPlayer = score;
        }

        public void setOrder() {
            this.cnt++;
        }
    }

}