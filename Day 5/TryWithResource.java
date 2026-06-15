import java.util.InputMismatchException;
import java.util.Scanner;

public class TryWithResource {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            var integerVar=sc.nextInt();
            System.err.println(integerVar);
            System.out.println(args[0]);
            args[0]=null;
            System.out.println(args[0].charAt(integerVar));
        }catch (InputMismatchException e){
            System.out.println("Number is invalid");

        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The command line arguements are empty");
        }catch (NullPointerException e){
            System.out.println("Null objects cannot be accessed");
        }
        
    }
}
