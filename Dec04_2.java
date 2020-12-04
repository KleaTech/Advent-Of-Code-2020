import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.regex.*;
import java.util.*;
import java.util.function.Predicate;

class Dec04_2 {
  public static void main(String[] args) throws IOException {
    var inputLines = Files.lines(Paths.get("Dec04_1_input.txt")).collect(Collectors.toList());
    String[] passports = new String[inputLines.size()];
    int i = 0;
    for (var line : inputLines) {
      if (line.isBlank()) i++;
      else passports[i] = passports[i] + " " + line;
    }
    var pattern = Pattern.compile("(...):(\\S+)");
    var validCount = Arrays.stream(passports)
      .filter(p -> p != null)
      .map(p -> {
        var matcher = pattern.matcher(p);
        var map = new HashMap<String, String>(8);
        while(matcher.find()) map.put(matcher.group(1), matcher.group(2));
        return Passport.parse(map);
      })
      .filter(p -> p != null)
      .filter(Passport::validate)
      .count();
    System.out.println(validCount);
  }

  static class Passport {
    String byr;
    String iyr;
    String eyr;
    String hgt;
    String hcl;
    String ecl;
    String pid;

    static Passport parse(Map<String, String> map) {
      if (map.size() < 7) return null;
      var passport = new Passport();
      for (var key : map.keySet()) {
        switch (key) {
          case "byr" : passport.byr = map.get(key); break;
          case "iyr" : passport.iyr = map.get(key); break;
          case "eyr" : passport.eyr = map.get(key); break;
          case "hgt" : passport.hgt = map.get(key); break;
          case "hcl" : passport.hcl = map.get(key); break;
          case "ecl" : passport.ecl = map.get(key); break;
          case "pid" : passport.pid = map.get(key); break;
          case "cid" : break;
          default: return null;
        }
      }
      if (passport.byr == null || passport.ecl == null || passport.eyr == null || passport.hcl == null || passport.hgt == null || passport.iyr == null || passport.pid == null) return null;
      return passport;
    }

    Predicate<String> number() { return s -> s.chars().allMatch(Character::isDigit); }
    Predicate<String> matches(String regex) { return s -> s.matches(regex); }
    Predicate<String> between(int lowBound, int highBound) { return s -> Long.parseLong(s) >= lowBound && Long.parseLong(s) <= highBound; }
    Predicate<String> substringWrapper(int start, int end, Predicate<String> originalPredicate) { return s -> originalPredicate.test(s.substring(start, end)); }
    boolean test(String str, Predicate<String>... predicates) {
      return Arrays.stream(predicates).reduce(Predicate::and).get().test(str);
    }

    boolean validate() {
      return 
        test(byr, number(), between(1920, 2002)) &&
        test(iyr, number(), between(2010, 2020)) &&
        test(eyr, number(), between(2020, 2030)) &&
        test(hcl, matches("#[0-9a-f]{6}")) &&
        test(ecl, matches("amb|blu|brn|gry|grn|hzl|oth")) &&
        test(pid, matches("[0-9]{9}")) &&
        test(hgt, 
          matches("[0-9]{2}in").and(substringWrapper(0, 2, between(59, 76)))
            .or(matches("[0-9]{3}cm").and(substringWrapper(0, 3, between(150, 193)))));
    }
  }
}