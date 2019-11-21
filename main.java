import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;

class Primary {
    public static void main(String[] args) throws IOException {  
        EmailExtraction ext = new EmailExtraction(args[0]);
        ext.sort();
        ext.printStats();
    }
}

class EmailExtraction {
    HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
    Stat[] sorted;

    EmailExtraction(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        String input = Files.readString(path);
        Pattern pattern = Pattern.compile("(?<=\\s|^)[\\w.'_%+-]+@(\\w+(\\.\\w+)+)(?=\\s|$)");
        Matcher matcher = pattern.matcher(input);
        var hasMatch = true;
        while(hasMatch) {
            hasMatch = matcher.find();
            if(hasMatch) {
                if(!this.frequencies.containsKey(matcher.group(1))) {
                    this.frequencies.put(matcher.group(1), 1);
                } else {
                    this.frequencies.put(matcher.group(1), this.frequencies.get(matcher.group(1)) + 1);
                }
            }
        }
    }

    public void printStats() {
        for(String i:this.frequencies.keySet()) {
            System.out.print(i);
            System.out.print(": ");
            System.out.println(this.frequencies.get(i));
        }
    }

    public void sort() {
        Stat[] mapAsArray = new Stat[this.frequencies.size()];
        int firstEmpty = 0;
        for(String i:this.frequencies.keySet()) {
            mapAsArray[firstEmpty] = new Stat(this.frequencies.get(i), i);
            firstEmpty++;
        }
        java.util.Arrays.sort(mapAsArray);
        this.sorted = mapAsArray;
    }
}

class Stat implements Comparable<Stat>{
    int frequency;
    String domain;

    public Stat(int _frequency, String _domain) {
        frequency = _frequency;
        domain = _domain;
    };

    @Override
    public int compareTo(Stat s) {
        return this.frequency - s.frequency;
    }
}
