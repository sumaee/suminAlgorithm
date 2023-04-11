class Solution {
    public int solution(String skill, String[] skill_trees) {
        
        int answer = 0;
        for(String skill_tree: skill_trees){
            String tmp = skill_tree;
            
            //skill_tree를 쪼개서 확인하면서 만약 skill에 포함되어 있지 않은 알파벳이라면
            //tmp에 그 알파벳을 제외하고 저장
            for(int i=0; i<skill_tree.length() ; i++){
                if(!skill.contains(String.valueOf(skill_tree.charAt(i)))){
                    tmp = tmp.replace(String.valueOf(skill_tree.charAt(i)), "");
                }
            }
            
            //tmp를 skill에서 찾을 때 indexOf의 값이 0이라면 배울 수 있는 스킬임
            if(skill.indexOf(tmp)==0)
                answer++;
            
        }
        
        return answer;
    }
}