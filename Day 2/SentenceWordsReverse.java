import java.util.Scanner;

public class SentenceWordsReverse {
    public static void main(String[] args) {
        StringBuilder res = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter setence");
        String a = sc.nextLine();

        String[] s = a.split(" ");
        for (int i = s.length - 1; i >= 0; i--) {
            res.append(s[i]);
            res.append(" ");
        }
        System.out.println(res);
        sc.close();

    }
}