//javac Dec05_2.java -cp ./streamex-0.7.3.jar
//java -cp .:./streamex-0.7.3.jar Dec05_2
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import one.util.streamex.*;
import java.util.*;
import java.util.stream.*;

class Dec05_2 {
  public static void main(String[] args) throws IOException {
    var IDs = Files.lines(Paths.get("Dec05_1_input.txt")).parallel().mapToInt(l -> {
      var chList = l.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
      Collections.reverse(chList);
      var row = EntryStream.of(chList).skip(3).filterValues(c -> c == 'B').mapKeyValue((i, c) -> 1 << (i-3)).mapToInt(i -> i).sum();
      var column = EntryStream.of(chList).limit(3).filterValues(c -> c == 'R').mapKeyValue((i, c) -> 1 << i).mapToInt(i -> i).sum();
      return row * 8 + column;
    }).sorted().toArray();
    int previousId = IDs[0] - 1;
    for (var id : IDs)
      if ((id - 1) != previousId) break;
      else previousId = id;
    System.out.println(previousId + 1);
  }
}