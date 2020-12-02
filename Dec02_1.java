import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.stream.Collectors;

class Dec02_1 {
  public static void main(String[] args) throws IOException {
    var validCount = Files.lines(Paths.get("Dec02_1_input.txt"))
      .map(Entry::parse)
      .filter(e -> {
          var occurences = e.password.chars().filter(c -> c == e.character).count();
          return occurences >= e.minOccurance && occurences <= e.maxOccurance;
        })
      .count();
    System.out.println(validCount);
  }

  static class Entry {
    int minOccurance;
    int maxOccurance;
    char character;
    String password;

    static Entry parse(String line) {
      return new Entry() {{
        var split = line.split("-|: | ");
        minOccurance = Integer.parseInt(split[0]);
        maxOccurance = Integer.parseInt(split[1]);
        character = split[2].charAt(0);
        password = split[3];
      }};
    }
  }
}