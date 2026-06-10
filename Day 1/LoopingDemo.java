
import java.util.Scanner;
class LoopingDemo{
    public static void main(String args[]){
        var sc=new Scanner(System.in);

        int age = 0;
        
        do{
            if(age==0)
                System.out.print("\nenter your age: ");
            else
                System.out.print("\nInvalid driver age\nre-enter your age: ");
            age=sc.nextInt();
        }while(age<18 || age>=60);
        System.out.print("\n Driver age is accepted ");
        sc.close();
    }
}