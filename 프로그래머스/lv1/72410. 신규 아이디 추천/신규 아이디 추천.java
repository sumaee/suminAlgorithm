class Solution {
    public String solution(String new_id) {
        String answer = "";
        answer=new_id.toLowerCase().replaceAll("[^a-z0-9-_.]", "");
        
        //3단계 : 연속된 .. 바꾸기
        while (answer.contains("..")) {
			answer = answer.replace("..", ".");
		}

		// 4단계 : 맨 앞, 맨 뒤의 .삭제
		if (answer.length() > 0 && answer.charAt(0) == '.') {
			answer = answer.replaceAll("^\\.", "");
		}
		if (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
			answer = answer.replaceAll("\\.$", "");
		}

		// 5단계 : 빈 문자열이라면 a채우기
		if (answer.isEmpty()) {
			answer = "a";
		}
		
		//6단계 : 길이가 16자 이상이면 15 개를 제외한 나머지 제외
		if(answer.length()>=16) {
			answer=answer.substring(0, 15);
			if (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
				answer = answer.replaceAll("\\.$", "");
			}
		}
		
		//7단계 : 2자 이하면 마지막 문자를 길이가 3이 될 때 까지 반복
		if(answer.length()<=2) {
			while(answer.length()<3) {
				answer+=answer.charAt(answer.length()-1);
			}
		}
        return answer;
    }
}