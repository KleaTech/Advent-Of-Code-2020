import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Dec11_1 {
  static char[][] array;
  static int n, m;

  public static void main(String[] args) throws IOException {
    var lines = Files.lines(Paths.get("Dec11_1_input.txt")).collect(Collectors.toList());
    n = lines.size();
    m = lines.get(0).length();
    array = new char[n][m];
    for (int i=0; i<n; i++)
      for (int j=0; j<m; j++)
        array[i][j] = lines.get(i).charAt(j);
    while(cycle()) ;
    int occupied = 0;
    for (int i=0; i<n; i++)
      for (int j=0; j<m; j++)
        if (array[i][j] == '#') occupied++;
    System.out.println(occupied);
  }

  static boolean cycle() {
    var copy = Arrays.stream(array).map(el -> el.clone()).toArray(char[][]::new);
    boolean changes = false;
    for (int i=0; i<n; i++)
      for (int j=0; j<m; j++) {
        if      (array[i][j] == '#' && countOfAdjacentOccupied(i,j) >= 4) {
          copy[i][j] = 'L';
          changes = true;
        }
        else if (array[i][j] == 'L' && countOfAdjacentOccupied(i,j) == 0) {
          copy[i][j] = '#';
          changes = true;
        }
      }
    array = copy;
    return changes;
  }

  static long countOfAdjacentOccupied(int i, int j) {
    return Stream.of(get(i-1,j), get(i+1,j), get(i-1,j+1), get(i-1,j-1), get(i+1,j-1), get(i+1,j+1), get(i,j-1), get(i,j+1)).filter(s -> s == '#').count();
  }

  static char get(int i, int j) {
    if (i >=0 && j >= 0 && i < n && j < m) return array[i][j];
    return 'a';
  }
}