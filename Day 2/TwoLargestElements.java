public class TwoLargestElements {

    public static void findTwoLargest(int[] arr) {
        // Step 1: Edge cases
        if (arr == null || arr.length < 2) {
            System.out.println("Array must have at least 2 elements.");
            return;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        // Step 2: Single pass using two variables
        for (int num : arr) {
            if (num > first) {
                // new maximum -> shift first to second, update first
                second = first;
                first = num;
            } else if (num > second && num < first) {
                // strictly less than first but greater than second
                second = num;
            }
            // if num == first, ignore (already captured in first)
        }

        // Step 3: Output result
        if (second == Integer.MIN_VALUE) {
            System.out.println("No second largest distinct element found "
                               + "(all elements may be equal).");
        } else {
            System.out.println("Largest : " + first);
            System.out.println("Second largest : " + second);
        }
    }

    // Test with different arrays including duplicates
    public static void main(String[] args) {
        int[][] testCases = {
            {10, 5, 20, 20, 15, 10},        // duplicates of max
            {50, 50, 50, 50},               // all equal
            {3, 1},                         // exactly two distinct
            {7},                            // single element (edge)
            {8, 9, 9, 4, 6},               // max repeated, second distinct
            {100, 50, 100, 25, 75, 75}      // repeated max and second
        };

        for (int[] arr : testCases) {
            System.out.println("Array: " + java.util.Arrays.toString(arr));
            findTwoLargest(arr);
            System.out.println("---------");
        }
    }
}