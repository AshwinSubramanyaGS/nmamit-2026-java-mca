import java.util.*;

public class Q9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int value = 2;
        while (Math.abs(value) <= n) {
            System.out.print(value + " ");
            value = value > 0 ? -(value + 2) : -(value - 2);
        }
        sc.close();
    }
}
