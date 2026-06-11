//1, 4, 7, 12, 23, ... N
import java.util.*;

public class Q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int term1 = 1;
        int term2 = 4;
        int term3 = 7;

        while (term1 <= n) {
            System.out.print(term1 + " ");
            int temp=term1;
            term1=term2;
            term2=term3;
            term3=temp+term1+term2;
        }
        sc.close();
    }
}
