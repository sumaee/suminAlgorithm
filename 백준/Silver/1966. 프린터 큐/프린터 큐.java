import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            //문서의 개수
            int n = Integer.parseInt(st.nextToken());
            //궁금한 문서의 인덱스
            int m = Integer.parseInt(st.nextToken());

            Queue<Document> que = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            //큐에 문서 넣기, 넣으면서 중요도
            for (int i = 0; i < n; i++) {
                que.offer(new Document(i, Integer.parseInt(st.nextToken())));
            }

            //원하는 인덱스 값이 나올때까지 찾기
            int cnt = 0;
            while (true) {
                Document document = que.poll();
                boolean flag = false;

                //지금보다 더 큰 중요도가 있는지 판단
                for (Document doc : que) {
                    //지금 문서보다 더 큰 중요도가 있다면 flag true로 바꾸고 for문 탈출
                    if (doc.num > document.num) {
                        flag = true;
                        break;
                    }
                }

                //현재 문서보다 중요도가 높은 것이 있다는 뜻이므로 해당 문서가 있는 곳까지 앞에 문서들 빼서 넣기
                if (flag) {
                    que.offer(document);
                }
                //현재 문서가 제일 중요도가 높다는 뜻이므로 궁금한 문서의 인덱스와 같은지 확인 아니라면 cnt++
                else {
                    cnt++;
                    if (document.idx == m) break;
                }
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}

class Document {
    int idx, num;

    public Document(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }
}
