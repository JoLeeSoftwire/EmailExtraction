import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class EmailExtraction {
    public static void main(String[] args) throws IOException {
        int count = 0;
        Path path = Paths.get(args[0]);
        String input = Files.readString(path);
        Pattern pattern = Pattern.compile("[\\w.'_%+-]+@softwire.com");
        Matcher matcher = pattern.matcher(input);
        var hasMatch = true;
        while(hasMatch) {
            hasMatch = matcher.find();
            if(hasMatch) {count ++; System.out.println(matcher.group());}
        }
        

        System.out.println(count);
    }
}