public class FirstNonRepeating {
    public static char firstUniqChar(String s) {

        int[] count = new int[256];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (count[c] == 1) {
                return c;
            }
        }

        return '_';

        /*
         * One line streams solution
         * return s.chars()
         * .mapToObj(c -> (char) c)
         * .collect(java.util.stream.Collectors.groupingBy(
         * c -> c,
         * java.util.LinkedHashMap::new,
         * java.util.stream.Collectors.counting()))
         * .entrySet().stream()
         * .filter(e -> e.getValue() == 1)
         * .map(java.util.Map.Entry::getKey)
         * .findFirst()
         * .orElse('_');
         */
    }

    public static void main(String[] args) {
        Object[][] tests = {
                { "swiss", 'w' },
                { "aabb", '_' },
                { "", '_' },
                { "a", 'a' },
                { "abacabad", 'c' },
                { "!@!!@", '@' },
                { "aaabbbccc", '_' }
        };
        int passed = 0;
        for (int i = 0; i < tests.length; i++) {
            String input = (String) tests[i][0];
            char expected = (char) tests[i][1];
            char result = firstUniqChar(input);
            if (expected == result) {
                passed++;
                System.out.println("Test " + (i + 1) + " passed");
            } else {
                System.out.println("Test " + (i + 1) + " FAILED");
                System.out.println("Input: " + input);
                System.out.println("Expected: " + expected + " but got: " + result);
            }
        }
        System.out.println(passed + "/" + tests.length + " tests passed");
    }
}