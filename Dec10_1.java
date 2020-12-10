import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Dec10_1 {
  public static void main(String[] args) throws IOException {
    class State { int diff1; int diff3; }
    final var state = new State();
    Files.lines(Paths.get("Dec10_1_input.txt")).mapToInt(Integer::parseInt).sorted().reduce(0, (a,b) -> {
      if (b-a==1) state.diff1++;
      else if (b-a==3) state.diff3++;
      return b;
    });
    System.out.println(state.diff1 * (state.diff3+1));
  }
}