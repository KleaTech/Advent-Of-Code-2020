import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Dec08_1 {
  public static void main(String[] args) throws IOException {
    var lines = Files.lines(Paths.get("Dec08_1_input.txt")).collect(Collectors.toList());
    var linesHit = new TreeSet<Integer>();
    var acc = 0;
    for (int pc=0; !linesHit.contains(pc);) {
      linesHit.add(pc);
      var value = Integer.parseInt(lines.get(pc).split(" ")[1]);
      switch(lines.get(pc).split(" ")[0]) {
        case "acc": acc += value; pc++; break;
        case "jmp": pc += value; break;
        default: pc++;
      }
    }
    System.out.println(acc);
  }
}