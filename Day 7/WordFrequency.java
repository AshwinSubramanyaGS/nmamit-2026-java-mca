import java.util.*;
import java.util.Map.Entry;

public class WordFrequency {
    public static String wordFrequency(List<String> words) {
        HashMap<String , Integer> map=new HashMap<>();
        for(String word:words ){
                map.put(word,map.getOrDefault(word,0)+1);
        }
        String result=new String();
        List<String> str=new ArrayList<>();
        for(Entry<String, Integer> entry:map.entrySet()){
            str.add(entry.getKey() +": "+entry.getValue());
        }
        str.sort((e1,e2)->e1.compareTo(e2));
        result=String.join("\n", str);
        return result;
        
    }

    public static void main(String[] args) {
        // Test cases: each element is {input list, expected output string}
        Object[][] tests = {
                { Arrays.asList("banana", "apple", "banana", "apple", "cherry"),
                        "apple: 2\nbanana: 2\ncherry: 1" },
                { Arrays.asList("z", "a", "z"),
                        "a: 1\nz: 2" },
                { Collections.emptyList(),
                        "" },
                { Arrays.asList("single"),
                        "single: 1" },
                { Arrays.asList("aaa", "aaa", "aaa", "aaa"),
                        "aaa: 4" }
        };
        int passed = 0;
        for (int i = 0; i < tests.length; i++) {
            List<String> input = (List<String>) tests[i][0];
            String expected = (String) tests[i][1];
            String result = wordFrequency(input);
            if (expected.equals(result)) {
                passed++;
                System.out.println("Test " + (i + 1) + " passed");
            } else {
                System.out.println("Test " + (i + 1) + " FAILED");
                System.out.println("Expected:\n" + expected);
                System.out.println("Got:\n" + result);
            }
        }
        System.out.println(passed + "/" + tests.length + " tests passed");
    }
}