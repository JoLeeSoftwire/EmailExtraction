import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class EmailExtraction {
    public static void main(String[] args) throws IOException {
        String emailString = "@softwire.com";
        int len = emailString.length();
        int count = 0;
        Path path = Paths.get(args[0]);
        String input = Files.readString(path);
        for(var i = 0; i<input.length(); i++) {
            if(i+len < input.length()) {
                if(input.substring(i, i+len).equals(emailString)) {
                    count++;
                }
            } else {
                if(input.substring(i).equals(emailString)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}