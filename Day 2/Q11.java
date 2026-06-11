import java.util.*;

public class Q11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print("2 ");
        for (int num = 2; num <= n; num++) {
            boolean prime = true;

            for (int i = 2; i <=(int) Math.sqrt(num)+1; i++) {
                if (num % i == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                System.out.print(num + " ");
            }
        }
        sc.close();
    }
}
