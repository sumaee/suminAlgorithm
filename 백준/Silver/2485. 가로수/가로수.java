import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //이미 심어져 있는 가로수
        int n = Integer.parseInt(br.readLine());

        //가로수들의 위치를 저장
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);

        //가로수들 사이의 간격을 저장할 set
        HashSet<Integer> dis = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            dis.add(list.get(i + 1) - list.get(i));
        }
        if (dis.size() == 1) {
            System.out.println(0);
            System.exit(0);
        }

        List<Integer> dist = new ArrayList<>(dis);

        //가로수들 사이의 간격들의 최대 공약수 구하기
        int gcd = getGcd(dist.get(1), dist.get(0));
        for (int i = 2; i < dist.size(); i++) {
            int tree = dist.get(i);
            gcd = tree > gcd ? getGcd(tree, gcd) : getGcd(gcd, tree);
        }

        //같은 간격으로 심었을 때의 나무 개수
        int totalTree = (list.get(list.size() - 1) - list.get(0)) / gcd + 1;
        int needTree = totalTree - list.size();

        System.out.println(needTree);
    }

    private static int getGcd(int a, int b) {
        return b != 0 ? getGcd(b, a % b) : a;
    }

}
