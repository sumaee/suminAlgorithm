class Solution {
    public int solution(int storey) {
       
        int answer = 0;
        //0층에 도착할 때까지 진행
        while (storey > 0) {
            int mod = storey % 10;
            storey /= 10;

            //만약 1의 자리가 5보다 크다면
            if (mod > 5) {
                answer += 10 - mod;
                storey++;
            }
            //5보다 작다면
            else if (mod < 5) {
                answer += mod;
            }
            //앞의 자리를 10으로 나눴을 때 나머지가 5보다 크거나 같다면
            else if (storey % 10 >= 5) {
                answer += 5;
                storey++;
            } else {
                answer += 5;
            }
        }
        return answer;
    }
}
