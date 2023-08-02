import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        //우선 제일 높은 알고력과 고딩력을 가진 거를 풀면 되는 것이므로 목표 문제의 알고력, 코딩력 구하기
        int lastAlp = 0;
        int lastCop = 0;
        for (int[] problem : problems) {
            lastAlp = Math.max(lastAlp, problem[0]);
            lastCop = Math.max(lastCop, problem[1]);
        }
        
        //초기 능력치로 다 풀수 있다면
        if (alp >= lastAlp && cop >= lastCop) {
            return 0;
        }
        
        //만약 초기 알고력만 모든 문제들을 만족한다면 초기 능력치를 최대 알고력으로 맞추기
        if (alp >= lastAlp) {
            alp = lastAlp;
        }

        //만약 초기 코딩력만 모든 문제들을 만족한다면 초기 능력치를 최대 코딩력으로 맞추기
        if (cop >= lastCop) {
            cop = lastCop;
        }

        //dp[i][j] = 알고력 i 와 코딩력 j를 달성하는 데 걸리는 소요 시간
        int[][] dp = new int[lastAlp + 2][lastCop + 2];

        //dp 우선 최대값으로 채워주기
        for (int i = 0; i <= lastAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        //초기 알고력과 코딩력 위치부터 시작
        dp[alp][cop] = 0;
        for (int i = alp; i <= lastAlp; i++) {
            for (int j = cop; j <= lastCop; j++) {
                //코딩력만 높히는 경우 이미 채워져 있는 것과 현재 시간에서 한시간 소요해서 늘리는 것 중 작은 거로 갱신
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                //알고력만 높히는 경우 이미 채워져 있는 것과 현재 시간에서 한시간 소요해서 늘리는 것 중 작은 거로 갱신
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);

                //문제를 돌면서 해당 문제를 풀수 있는 능력치가 된다면 문제를 풀어서 다음 능력치를 채움
                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        //현재 알고력 + 문제보상 알고력 과 현재 코딩력 + 문제보상 코딩력이 최대 능력치를 벗어난다면
                        if (i + problem[2] > lastAlp && j + problem[3] > lastCop) {
                            //최대 능력치는 (현재 시간+문제 소요 시간) VS (이미 있는 소요시간) 중 최솟값으로 저장
                            dp[lastAlp][lastCop] = Math.min(dp[i][j] + problem[4], dp[lastAlp][lastCop]);
                        }

                        //만약 문제를 풀었을 때 알고력만 한계치를 넘어간다면
                        else if (i + problem[2] > lastAlp) {
                            //알고력의 최대 능력치는 (현재시간 + 문제 소요시간) VS (이미 있는 소요시간) 중 최솟값으로 저장
                            dp[lastAlp][j + problem[3]] = Math.min(dp[i][j] + problem[4], dp[lastAlp][j + problem[3]]);
                        }

                        //만약 문제를 풀었을 때 코딩력만 한계치를 넘어간다면
                        else if (j + problem[3] > lastCop) {
                            //코딩력의 최대 능력치는 (현재시간 + 문제 소요시간) VS (이미 있는 소요시간) 중 최솟값으로 저장
                            dp[i + problem[2]][lastCop] = Math.min(dp[i][j] + problem[4], dp[i + problem[2]][lastCop]);
                        }

                        //알고력과 코딩력 둘다 한계치를 안넘어 간다면
                        else if (i + problem[2] <= lastAlp && j + problem[3] <= lastCop) {
                            //다음 능력치 최솟값 갱신
                            dp[i + problem[2]][j + problem[3]] = Math.min(dp[i][j] + problem[4], dp[i + problem[2]][j + problem[3]]);
                        }
                    }
                }
            }
        }

        return dp[lastAlp][lastCop];
    }
}