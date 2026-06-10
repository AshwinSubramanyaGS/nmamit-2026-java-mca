public class Q14 {
    public static void main(String[] args) {
        int count = 0;

        for (int num = 7; ; num += 7) {
            if (num % 2 == 1 &&
                num % 3 == 1 &&
                num % 4 == 1 &&
                num % 5 == 1 &&
                num % 6 == 1) {

                count++;
                if (count == 1 || count == 2 || count == 4) {
                    System.out.println(num);
                }

                if (count == 4) {
                    break;
                }
            }
        }
    }
}
