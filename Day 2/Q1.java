
import java.util.*;

public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int num = 1;
        while (num <= n) {
            System.out.print(num + (num < n ? ", " : ""));
            num++;
        }
        sc.close();
    }
}
