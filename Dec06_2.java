import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class Dec06_2 {
  public static void main(String[] args) throws IOException {
    var sum = Arrays.stream(Files.readString(Paths.get("Dec06_1_input.txt")).split("\\n\\n|\\r\\n\\r\\n")).mapToLong(group -> 
        group.lines()
             .map(Dec06_2::stringToCharSet)
             .reduce(stringToCharSet("abcdefghijklmnopqrstuvwxyz"), (acc, line) -> { acc.retainAll(line); return acc; }).size())
      .sum();
    System.out.println(sum);
  }

  static Set<Character> stringToCharSet(String str) {
    return str.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
  }
}