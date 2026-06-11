/*
Java's System.out.printf function can be used to print formatted output. The purpose of this exercise is to test your understanding of formatting output using printf.

To get you started, a portion of the solution is provided for you in the editor; you must format and print the input to complete the solution.

Input Format

Every line of input will contain a String followed by an integer.
Each String will have a maximum of  alphabetic characters, and each integer will be in the inclusive range from  to .

Output Format

In each line of output there should be two columns:
The first column contains the String and is left justified using exactly  characters.
The second column contains the integer, expressed in exactly  digits; if the original input has less than three digits, you must pad your output's leading digits with zeroes.

Sample Input

java 100
cpp 65
python 50
Sample Output

================================
java           100 
cpp            065 
python         050 
================================
Explanation

Each String is left-justified with 15 trailing whitespace through the first  characters. The leading digit of the integer is 16th the  character, and each integer that was less than 3 digits now has leading zeroes.
*/

import java.util.Scanner;

public class HrrJavaFout {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String s[]=new String[3];
        int n[]=new int[3];
        
        for(int i=0;i<3;i++){
            s[i]=sc.next();
            n[i]=sc.nextInt();    
        }
        sc.close();
        System.out.println("================================");
        
        for(int i=0;i<3;i++)
            System.out.printf("%-15s%03d\n",s[i],n[i]);
            
        System.out.println("================================");
        
        
        
    }
    
}
