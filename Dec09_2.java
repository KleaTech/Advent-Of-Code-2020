import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Dec09_2 {
  public static void main(String[] args) throws IOException {
    var lines = Files.lines(Paths.get("Dec09_1_input.txt")).map(Long::parseLong).collect(Collectors.toList());
    var number = findNumber(lines);
    int low=0, high=0;
    for (;;low++)
      for (high=low+1;; high++) {
        var sum = lines.stream().skip(low).limit(high-low+1).mapToLong(i->i).sum();
        if (sum > number) break;
        if (sum == number) {
          System.out.println(lines.stream().skip(low).limit(high-low+1).mapToLong(i->i).min().getAsLong() + lines.stream().skip(low).limit(high-low+1).mapToLong(i->i).max().getAsLong());
          return;
        }
      }
  }

  static long findNumber(List<Long> input) {
    var preLength = 25;
    main: for (int i=preLength; i<input.size(); i++) {
      for (int j=(i-preLength); j<i; j++) {
        for (int k=(i-preLength); k<i; k++) {
          if (j==k) continue;
          if (input.get(j) + input.get(k) == input.get(i)) continue main; 
        }
      }
      return input.get(i);
    }
    throw new RuntimeException();
  }
}