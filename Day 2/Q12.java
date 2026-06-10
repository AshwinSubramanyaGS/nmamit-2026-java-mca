public class Q12 {
    public static void main(String[] args) {
        int value = 2;
        int diff = 1;

        for (int i = 0; i < 11; i++) {
            System.out.print(value + " ");
            value += diff;
            diff += 3;
        }
    }
}
