import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();

        //각 장르의 총 합 구하기
        for(int i=0; i<genres.length; i++)
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);

        //장르만 뽑아내서 내림차순으로 정리
        List<String> genre = new ArrayList<>();
        for(String g : map.keySet())
            genre.add(g);
        Collections.sort(genre, (play1, play2)-> map.get(play2)-map.get(play1));

        //장르를 돌며 재생수가 많은 것 저장
        // 두개까지밖에 저장을 못하므로 그냥 2번 돌리기
        List<Integer> play = new ArrayList<>();
        for(int i=0; i<genre.size(); i++){
            int firstMax = 0;
            int firstIdx = 0;

            //genres 배열을 돌다가 genre의 배열과 같고 palys 값이 현재 max보다 크다면 갱신
            for(int j=0; j<genres.length; j++){
                if(genre.get(i).equals(genres[j]) && firstMax<plays[j]){
                    firstMax = plays[j];
                    firstIdx = j;
                }
            }

            int secondMax = 0;
            int secondIdx = -1;

            //두번째이므로 첫번째로 지정한 firstIdx랑 같지 않은 곳에서 확인해야함
            for(int j=0; j<genres.length; j++){
                if(j!=firstIdx && genre.get(i).equals(genres[j])&& secondMax<plays[j]){
                    secondMax =plays[j];
                    secondIdx = j;
                }
            }

            play.add(firstIdx);
            if(secondIdx!=-1) play.add(secondIdx);

        }

        int[] answer = new int[play.size()];
        for(int i=0; i<play.size(); i++){
            answer[i] = play.get(i);
        }

        return answer;
    }
}