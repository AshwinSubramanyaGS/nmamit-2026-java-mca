import java.util.*;

public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int num = 1;
        int increment = 1;

        while (num <= n) {
            System.out.print(num + " ");
            num += increment++;
        }
        sc.close();
    }
}
