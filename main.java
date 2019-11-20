import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;

class EmailExtraction {
    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
        Path path = Paths.get(args[0]);
        String input = Files.readString(path);
        Pattern pattern = Pattern.compile("\\s[\\w.'_%+-]+@(\\w+.\\w+)\\s");
        Matcher matcher = pattern.matcher(input);
        var hasMatch = true;
        while(hasMatch) {
            hasMatch = matcher.find();
            if(hasMatch) {
                if(!frequencies.containsKey(matcher.group(1))) {
                    frequencies.put(matcher.group(1), 1);
                } else {
                    frequencies.put(matcher.group(1), frequencies.get(matcher.group(1)) + 1);
                }
                // System.out.println(matcher.group());
            }
        }
        for(String i:frequencies.keySet()) {
            System.out.print(i);
            System.out.print(": ");
            System.out.println(frequencies.get(i));
        }
    }
}