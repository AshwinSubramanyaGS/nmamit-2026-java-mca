import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class RemoveEven {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();

        ArrayList<Integer> list= new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(sc.nextInt());
        }

        Iterator<Integer> it= list.iterator();

        while(it.hasNext()){
            if(it.next()%2==0)
                it.remove();
        }

        for(int x: list){
            System.out.print(x+" ");
        }
    }
}
