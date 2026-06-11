import java.util.Scanner;

public class PatternAscii {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size");
        int num = sc.nextInt();
        sc.close();

        for(int i=0;i<num;i++){
            int j=0;
            for(j=0;j<num;j++){
                System.out.print( (j<num-i?(char)('A'+j):' ') + " ");
            }
            
            j-=2;
        
            for(;j>=0;j--){
                System.out.print( (j<num-i?(char)('A'+j):' ') + " ");
            }
            System.out.println();
        }
    }
}