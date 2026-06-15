

public class UnreachCodeErrorDemo {
    public static void main(String[] args) {
        try{
            System.out.println(args[0]);
            int a=1/0;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Command line arg are empty");
        }
        catch(ArithmeticException e){
            System.out.println("Cannot divide by 0");
        }
        catch(Throwable e){
            System.out.println("Unknown error has occured please contact");
        }

    }
}
