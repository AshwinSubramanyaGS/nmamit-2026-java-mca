public class InLineDef {
    public static void main(String[] args) {
        /* Runnable ob = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 11; i++)
                    System.out.println("10 X " + i + " = " + 10 * i);
            }
        }; */
        Runnable ob = () -> {
            for (int i = 1; i < 11; i++)
                System.out.println("10 X " + i + " = " + 10 * i);
        };

        new Thread(ob).start();
        System.out.println("End of main");

    }
}
