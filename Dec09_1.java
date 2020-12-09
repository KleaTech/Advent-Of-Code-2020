import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Dec09_1 {
  public static void main(String[] args) throws IOException {
    var lines = Files.lines(Paths.get("Dec09_1_input.txt")).map(Long::parseLong).collect(Collectors.toList());
    var preLength = 25;
    main: for (int i=preLength; i<lines.size(); i++) {
      for (int j=(i-preLength); j<i; j++) {
        for (int k=(i-preLength); k<i; k++) {
          if (j==k) continue;
          if (lines.get(j) + lines.get(k) == lines.get(i)) continue main; 
        }
      }
      System.out.println(lines.get(i));
      return;
    }
  }
}