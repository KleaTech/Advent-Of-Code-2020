import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
//I'm not proud of this solution, but I spend way too much time on this puzzle, and I'm two day behind, so I'll leave it. It works.
class Dec10_2 {
  static long count = 0;
  static List<List<Integer>> sublists = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    var list = IntStream.concat(IntStream.of(0), Files.lines(Paths.get("Dec10_1_input.txt")).mapToInt(Integer::parseInt)).sorted().boxed().collect(Collectors.toList());
    list.add(list.get(list.size()-1) + 3);
    int lastIndex = 0;
    for (int i=0; i<list.size()-1; i++) {
        if (list.get(i+1) - list.get(i) == 3) {
            sublists.add(list.subList(lastIndex, i+1));
            lastIndex = i+1;
        }
    }
    count = sublists.stream().map(s -> {
        possibleNumbers(s.get(0), s);
        long c = count;
        count = 0;
        return c;
    }).reduce(1l, (a,b) -> a*b);
    
    System.out.println(count);
  }

  static void possibleNumbers(int prev, List<Integer> list) {
    if (list.size() <= 2) {
        count++;
        return;
    }
    int i = 0;
    if (0 < (i = list.indexOf(prev+1))) possibleNumbers(prev+1, list.subList(i, list.size()));
    if (0 < (i = list.indexOf(prev+2))) possibleNumbers(prev+2, list.subList(i, list.size()));
    if (0 < (i = list.indexOf(prev+3))) possibleNumbers(prev+3, list.subList(i, list.size()));
  }
}