
public class UnNamedBlockDemo {
    public static void main(String[] args) {
        var ob=new MyClass();
        var ob2=new MyClass();
        var ob3=new MyClass();
        var ob4=new MyClass();

    }    
}

class MyClass{

    public MyClass() {
        System.out.println("Object created");
    }
    static{
        System.out.println("Static Initializer block");
    }
    {
        System.out.println("Initializer block");
    }
    
}
