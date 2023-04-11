class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
       
        //배열을 돌며 확인
        next:for(int i=0; i<prices.length-1;i++){
            //기준 숫자 다음부터 비교
            for(int j=i+1; j<prices.length ; j++){
                //만약 다음 숫자가 기준 숫자보다 작다면
                if(prices[i]>prices[j]){
                    //인덱스 차이만큼이 가격이 떨어지지 않은 기간
                    answer[i] = j-i;
                    //다음으로 넘어가기
                    continue next;
                }
            }
            //continue에 안걸렸다면 끝까지 간것이므로 총 길이에서 기준 숫자의 인덱스만큼 빼주기
            answer[i] = prices.length-i-1;
        }
        
        //마지막 숫자는 항상 0이므로 0 넣어주기
        answer[prices.length-1] = 0;
        return answer;
    }
}