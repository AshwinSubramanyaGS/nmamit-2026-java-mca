class MyMain{
    public static void main(String args[]){
        int var1 = 1;
        int var2 = 2;
        int var3 = -1;

        System.out.println( (var1>var2)?(var2>var3?var2:var3):(var1>var3?var1:var3));
    }
}