import java.util.Scanner;

public class ClassDemo {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no of Students: ");
        int no=sc.nextInt();
        

        Student students[]=new Student[no];
        for(int i=0;i<no;i++){
           sc.nextLine();
            System.out.printf("\nEnter name of Student[%d]: ",i+1);
            String name=sc.nextLine();
            System.out.printf("\nEnter usn of Student[%d]: ",i+1);
            String usn=sc.nextLine();
            System.out.printf("\nEnter cgpi of Student[%d]: ",i+1);
            double cgpi=sc.nextDouble();
            students[i]=new Student(name, usn, cgpi);
        }

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
        sc.close();
    }
}

class  Student {
    private String name;
    private String usn;
    private double cgpi;

    public Student(String name, String usn, double cgpi) {
        this.name = name;
        this.usn = usn;
        this.cgpi = cgpi;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsn() {
        return usn;
    }
    public void setUsn(String usn) {
        this.usn = usn;
    }
    
    public double getCgpi() {
        return cgpi;
    }
    public void setCgpi(double cgpi) {
        this.cgpi = cgpi;
    }
    @Override
    public String toString() {
        return "Student Details:\n"+"name=" + name + "\nusn=" + usn + "\ncgpi=" + cgpi ;
    }

    
}