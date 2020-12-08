import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Dec08_2 {
  public static void main(String[] args) throws IOException {
    var lines = Files.lines(Paths.get("Dec08_1_input.txt")).collect(Collectors.toList());
    for (int i=0; i<lines.size(); i++) {
      if (lines.get(i).startsWith("acc")) continue;
      var acc = 0;
      var changedLines = new ArrayList<String>(lines);
      var val = lines.get(i).split(" ")[1];
      changedLines.set(i, lines.get(i).startsWith("nop") ? "jmp " + val : "nop " + val);
      var linesHit = new TreeSet<Integer>();
      for (int pc=0; !linesHit.contains(pc);) {
        linesHit.add(pc);
        if (pc == lines.size()) {
          System.out.println(acc);
          System.exit(0);
        }
        var value = Integer.parseInt(changedLines.get(pc).split(" ")[1]);
        switch(changedLines.get(pc).split(" ")[0]) {
          case "acc": acc += value; pc++; break;
          case "jmp": pc += value; break;
          default: pc++;
        }
      }
    }
  }
}