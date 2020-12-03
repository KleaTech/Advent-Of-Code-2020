import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

class Dec03_1 {
  public static void main(String[] args) throws IOException {
    var inputLines = Files.lines(Paths.get("Dec03_1_input.txt")).collect(Collectors.toList());
    var lines = inputLines.stream().map(s -> s.repeat(((inputLines.size() / s.length()) + 1) * 3)).collect(Collectors.toList());
    int trees = 0;
    for (int i=0, j=0; i < lines.size(); i++, j+=3)
      if (lines.get(i).charAt(j) == '#')
        trees++;
    System.out.println(trees);
  }
}