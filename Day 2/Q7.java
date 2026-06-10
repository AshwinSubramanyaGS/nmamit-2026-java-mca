import java.util.Scanner;
public class Q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();
        int term = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(term + " ");

            term = (i % 2 == 0)
                    ? term + 4
                    : term - 4;
        }
        sc.close();
    }
}
