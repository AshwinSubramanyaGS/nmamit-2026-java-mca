import java.util.Scanner;
class MpcsExam{
    public static void main(String args[]){
        var sc=new Scanner(System.in);
        double trainee1  = 0;
        double trainee2  = 0;
        double trainee3  = 0;
        int input=0,i=1;
        for(boolean flag=true; i<=9&&flag ;i++){
            input=sc.nextInt();
            if(input<=1||input>100)
                flag=false;
            else if(i%3==1)
                trainee1+=input;
            else if(i%3==2)
                trainee2+=input;
            else if(i%3==0)
                trainee3+=input;
        }
        trainee1/=3;
        trainee2/=3;
        trainee3/=3;
        if(i<9)
            System.out.println("INVALID INPUT");
        else if( (trainee1+trainee2+trainee3)/3 < 70.00)
            System.out.println("All TRAINEES ARE UNFIT");
        else if(trainee1>trainee2&&trainee1>trainee3)
            System.out.println("Trainee: 1");
        else if(trainee2>trainee1&&trainee2>trainee3)
            System.out.println("Trainee: 2");
        else if(trainee3>trainee1&&trainee3>trainee2)
            System.out.println("Trainee: 3");
        else if(trainee3==trainee1&&trainee3>trainee2)
            System.out.println("Trainee: 1\nTrainee: 3");
        else if(trainee3==trainee2&&trainee3>trainee1)
            System.out.println("Trainee: 2\nTrainee: 3");
        else if(trainee1==trainee2&&trainee1>trainee3)
            System.out.println("Trainee: 1\nTrainee: 2");
        else if(trainee1==trainee2&&trainee1==trainee3)
            System.out.println("Trainee: 1\nTrainee: 2\nTrainee: 3");
        sc.close();
    }
}