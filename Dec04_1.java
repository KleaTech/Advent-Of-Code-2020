import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

class Dec04_1 {
  public static void main(String[] args) throws IOException {
    var inputLines = Files.lines(Paths.get("Dec04_1_input.txt")).collect(Collectors.toList());
    String[] passports = new String[inputLines.size()];
    int i = 0;
    for (var line : inputLines) {
      if (line.isBlank()) i++;
      else passports[i] = passports[i] + line;
    }
    var validCount = Arrays.stream(passports)
      .filter(e -> e != null)
      .filter(p -> p.contains("byr") && p.contains("iyr") && p.contains("eyr") && p.contains("hgt") && p.contains("hcl") && p.contains("ecl") && p.contains("pid"))
      .count();
    System.out.println(validCount);
  }
}