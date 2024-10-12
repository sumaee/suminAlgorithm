import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] things = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            things[i] = Integer.parseInt(st.nextToken());
        }

        //해당 제품이 콘센트를 사용하고 있는지 확인
        boolean[] nowUse = new boolean[101];
        int use = 0; // 현재 콘센트를 사용하고 있는 제품의 수
        int cnt = 0; // 플러그 빼는 횟수

        for (int i = 0; i < k; i++) {
            int curr = things[i];
            
            // 현재 콘센트를 사용안하는 제품이라면
            if (!nowUse[curr]) {
                //콘센트를 그냥 쓸 수 있으면 쓰기
                if (use < n) {
                    nowUse[curr] = true;
                    use++;
                } 
                //콘센트가 꽉찼다면 뽑을 제품 찾기
                else {
                    // 나중에도 쓰이는 제품인지 확인
                    List<Integer> afterUse = new ArrayList<>();
                    for (int j = i; j < k; j++) {
                        if (nowUse[things[j]] && !afterUse.contains(things[j])) {
                            afterUse.add(things[j]);
                        }
                    }
                    
                    // 나중에도 쓰인다면 제일 마지막으로 쓰이는 제품 뽑고 그자리에 현재 쓸 제품 사용
                    if (afterUse.size() >= n) {
                        nowUse[afterUse.get(afterUse.size() - 1)] = false;
                    } 
                    // 나중에 안쓰이는 제품이 있다면 그 제품 뽑고 그자리에 현재 쓸 제품 뽑기
                    else {
                        for (int j = 1; j < nowUse.length; j++) {
                            if (nowUse[j] & !afterUse.contains(j)) {
                                nowUse[j] = false;
                                break;
                            }
                        }
                    }

                    nowUse[curr] = true;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }
}