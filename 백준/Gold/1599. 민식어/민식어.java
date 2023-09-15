import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        IntStream.range(0, n)
                .mapToObj(value -> {
                    try {
                        return br.readLine().replace("k", "c").replace("ng", "n~");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sorted()
                .map(s -> s.replace("c", "k").replace("n~", "ng"))
                .forEach(System.out::println);
    }
}
