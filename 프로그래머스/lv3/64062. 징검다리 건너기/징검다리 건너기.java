class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        //최대로 건널수 있는 친구들의 명수 -> 모든 징검다리가 200000000 로 되어있을 때
        int max = 200000000;
        
        //최소로 건널 수 있는 친구 -> 모든 징검다리가 1 로 되어있을 때
        int min = 1;
        
        //200000000은 너무 크므로 이분탐색해서 명수를 찾기
        while(min<=max){
            int mid = (min+max)/2;
            
            //mid만큼의 사람이 건널 수 있다면 mid ~ max 사이의 이분탐색
            if(isPossible(mid, stones, k)){
                answer = mid;
                min = mid+1;                  
            }
            
            //mid 만큼의 사람이 건널 수 없다면 min~mid 사이의 이분탐색
            else
                max = mid-1;
        }
        
        return answer;
    }
    
    private boolean isPossible(int mid, int[] stones, int k){
        //0이 된 징검다리 연속 개수
        int no =0;
        
        for(int stone : stones){
            // 스톤의 값에서 사람수를 뺏을 때 음수가 나온다면 건널 수 없는 징검다리 세기
            if(stone - mid < 0){
                no++;
            }
            
            //뺏을 때 0이 아니라면 다시 리셋
            else
                no = 0;
            
            //no 가 k만큼 됬다면 다음 사람은 못건넘
            if(no == k)
                return false;
        }
        
        return true;
    }
}