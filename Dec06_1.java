import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

class Dec06_1 {
  public static void main(String[] args) throws IOException {
    var sum = Arrays.stream(Files.readString(Paths.get("Dec06_1_input.txt")).split("\\n\\n|\\r\\n\\r\\n")).mapToLong(g -> g.chars().filter(c -> c > 96).distinct().count()).sum();
    System.out.println(sum);
  }
}