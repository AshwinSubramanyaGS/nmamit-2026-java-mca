import java.util.*;

public class MaxWithout {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(sc.nextInt());
        sc.close();
        System.out.println(Collections.max(list));
    }
}