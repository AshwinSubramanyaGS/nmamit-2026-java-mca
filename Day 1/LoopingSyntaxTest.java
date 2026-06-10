class LoopingSyntaxTest{
    public static void main(String args[]){
        int i=1,j=100,k=12;
        for(i=-4,j=0,k=1;i<5;i+=100,j*=i)
        System.out.println("test");
    }
}