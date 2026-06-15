import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveEvenWith {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(sc.nextInt());
        
        sc.close();
        list.removeIf(i->i%2!=0);

        for(Integer i:list)
            System.out.print(i+" ");
    }
}
