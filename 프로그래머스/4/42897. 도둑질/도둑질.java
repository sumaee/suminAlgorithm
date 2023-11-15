class Solution {
    public int solution(int[] money) {
        int answer = 0;
        //집이 3개면 한집밖에 못털음
        if (money.length == 3) {
            for (int i = 0; i < 3; i++) {
                answer = Math.max(answer, money[i]);
            }

            return answer;
        }

        //첫번째 집을 무조건 털고 마지막은 안털었을 때
        int[] getFirstDp = new int[money.length];
        getFirstDp[0] = money[0];
        //첫집을 터는게 많은 지 두번째 집을 터는게 많은지
        getFirstDp[1] = Math.max(money[0], money[1]);


        //첫번째 집 안털때
        int[] noFirstDp = new int[money.length];
        noFirstDp[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            //이전집을 털고 현재 집을 안털기 vs 전전집을 털고 현재집을 털기
            noFirstDp[i] = Math.max(noFirstDp[i - 1], noFirstDp[i - 2] + money[i]);
            answer = Math.max(answer, noFirstDp[i]);

            //마지막 집은 안털기 때문에 여기서 한번 걸러주기
            if (i == money.length - 1) {
                break;
            }

            getFirstDp[i] = Math.max(getFirstDp[i - 1], getFirstDp[i - 2] + money[i]);
            answer = Math.max(answer, getFirstDp[i]);
        }

        return answer;
    }
}