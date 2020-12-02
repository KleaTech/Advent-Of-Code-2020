import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Dec02_2 {
  public static void main(String[] args) throws IOException {
    var validCount = Files.lines(Paths.get("Dec02_1_input.txt"))
      .map(Entry::parse)
      .filter(e -> e.password.charAt(e.position1 - 1) == e.character && e.password.charAt(e.position2 - 1) != e.character || 
                   e.password.charAt(e.position1 - 1) != e.character && e.password.charAt(e.position2 - 1) == e.character)
      .count();
    System.out.println(validCount);
  }

  static class Entry {
    int position1;
    int position2;
    char character;
    String password;

    static Entry parse(String line) {
      return new Entry() {{
        var split = line.split("-|: | ");
        position1 = Integer.parseInt(split[0]);
        position2 = Integer.parseInt(split[1]);
        character = split[2].charAt(0);
        password = split[3];
      }};
    }
  }
}