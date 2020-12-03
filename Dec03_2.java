import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.List;

class Dec03_2 {

  static List<String> lines;

  public static void main(String[] args) throws IOException {
    var inputLines = Files.lines(Paths.get("Dec03_1_input.txt")).collect(Collectors.toList());
    lines = inputLines.stream().map(s -> s.repeat(((inputLines.size() / s.length()) + 1) * 7)).collect(Collectors.toList());
    System.out.println(trees(1,1) * trees(1,3) * trees(1,5) * trees(1,7) * trees(2,1));
  }

  static long trees(int down, int right) {
    long trees = 0;
    for (int i=0, j=0; i < Dec03_2.lines.size(); i+=down, j+=right)
      if (Dec03_2.lines.get(i).charAt(j) == '#')
        trees++;
    System.out.println(trees);
    return trees;
  }
}