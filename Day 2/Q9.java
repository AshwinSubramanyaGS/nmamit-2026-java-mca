import java.util.*;

public class Q9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int value = 2;
        int counter=1;
        while (value <= n) {
            System.out.print(counter%2!=0? value :" "+ -value + " ");
            value+=2;
            counter++;
        }
        sc.close();
    }
}
