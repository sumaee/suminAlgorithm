import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> lenMap = new HashMap<>();
        Map<String, Integer> cntMap = new HashMap<>();
        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            wordSet.add(word);
            lenMap.put(word, word.length());
            cntMap.put(word, cntMap.getOrDefault(word, 0) + 1);
        }

        List<Word> wordList = new ArrayList<>();
        for (String word : wordSet) {
            if (lenMap.get(word) >= m) {
                wordList.add(new Word(cntMap.get(word), lenMap.get(word), word));
            }
        }

        wordList.sort((o1, o2) -> {
            if (o1.cnt == o2.cnt) {
                if (o1.len == o2.len) {
                    return o1.word.compareTo(o2.word);
                }
                return o2.len - o1.len;
            }
            return o2.cnt - o1.cnt;
        });

        StringBuilder sb = new StringBuilder();
        for (Word word : wordList) {
            sb.append(word.word).append("\n");
        }

        System.out.println(sb);
    }

    static class Word {
        int cnt, len;
        String word;

        public Word(int cnt, int len, String word) {
            this.cnt = cnt;
            this.len = len;
            this.word = word;
        }
    }

}