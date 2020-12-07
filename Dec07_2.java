import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

class Dec07_2 {
  public static void main(String[] args) throws IOException {
    var lines = Files.lines(Paths.get("Dec07_1_input.txt")).collect(Collectors.toList());
    var pattern = Pattern.compile("(\\d) (\\w+ \\w+)");
    var containedBags = new ArrayList<String>();
    containedBags.add("shiny gold");
    int count = 0;
    while (!containedBags.isEmpty()) {
      var candidates = new ArrayList<String>();
      for (var bag : containedBags) 
        for (var line : lines) 
          if (line.startsWith(bag)) {
            var map = new HashMap<String, Integer>(4);
            var matcher = pattern.matcher(line);
            while(matcher.find()) map.put(matcher.group(2), Integer.parseInt(matcher.group(1)));
            for (var entry : map.entrySet())
              for (int i=0; i < entry.getValue(); i++) {
                candidates.add(entry.getKey());
                count++;
              }
            break;
          }
      containedBags = candidates;
    }
    System.out.println(count);
  }
}