import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int num = 2;
        while (num*num <= n) {
            System.out.print((num * num) + " ");
            num += 2;
        }
        sc.close();
    }
}
