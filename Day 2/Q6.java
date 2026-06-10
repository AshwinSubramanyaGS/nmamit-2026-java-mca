import java.util.*;

public class Q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int num = 1;
        int increment = 3;

        while (num <= n) {
            System.out.print(num + " ");
            num += increment;
            increment += 3;
        }
        sc.close();
    }
}
