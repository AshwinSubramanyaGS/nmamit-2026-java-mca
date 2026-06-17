import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertDeletAt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        int q = sc.nextInt();
        for(int i=0;i<q;i++){
            if(sc.next().equals("insert")){
                int index=sc.nextInt();
                int value=sc.nextInt();
                list.add(index,value);
            }else{
                int index=sc.nextInt();
                list.remove(index);
            }    
        }
        sc.close();

        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
    }
}