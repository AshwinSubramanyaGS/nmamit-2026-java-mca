import java.util.*;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int num = 1;
        while (num <= n) {
            System.out.print(num + " ");
            num += 2;
        }
        sc.close();
    }
}
