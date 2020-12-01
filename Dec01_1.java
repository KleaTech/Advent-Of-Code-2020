import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.stream.Collectors;

class Dec01_1 {
  public static void main(String[] args) throws IOException {
    var values = Files.lines(Paths.get("Dec01_1_input.txt")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
    values.stream().parallel().forEach(i -> {
      for (var pair : values)
        if (i + pair == 2020) {
          System.out.println(i * pair);
          System.exit(0);
        }
    });
  }
}