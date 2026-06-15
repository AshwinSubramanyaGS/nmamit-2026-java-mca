import java.util.*;
import java.util.stream.*;

public class SumWith {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = IntStream.range(0, n)
                .map(i -> sc.nextInt())
                .boxed()
                .collect(Collectors.toList());
        sc.close();
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());
    }
}