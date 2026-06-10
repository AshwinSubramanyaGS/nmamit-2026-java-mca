import java.util.Scanner;

public class Q13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        int term = 4;

        int d1 = 3;
        int d2 = 3;

        System.out.print(term + " ");

        if (n >= 2) {
            term += d1;
            System.out.print(term + " ");
        }

        for (int i = 3; i <= n; i++) {
            int nextDiff = d1 + d2;

            term += nextDiff;
            System.out.print(term + " ");

            d1 = d2;
            d2 = nextDiff;
        }
        sc.close();
    }
}
