import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Dec07_1 {
  public static void main(String[] args) throws IOException {
    var lines = Files.lines(Paths.get("Dec07_1_input.txt")).collect(Collectors.toList());
    var result = new ArrayList<String>();
    var bagsContainedInOtherBags = new ArrayList<String>();
    bagsContainedInOtherBags.add("shiny gold");
    while (!bagsContainedInOtherBags.isEmpty()) {
      var candidates = new ArrayList<String>();
      for (var bag : bagsContainedInOtherBags) 
        for (var line : lines) 
          if (line.contains(bag) && !line.startsWith(bag)) {
            var qualifiedBag = line.split(" bags")[0];
            result.add(qualifiedBag);
            candidates.add(qualifiedBag);
          }
      bagsContainedInOtherBags = candidates;
    }
    System.out.println(result.stream().distinct().count());
  }
}