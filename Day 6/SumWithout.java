import java.util.*;

public class SumWithout {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(sc.nextInt());
        sc.close();
        int sum = 0;
        for (int num : list) sum += num;
        System.out.println(sum);
    }
}