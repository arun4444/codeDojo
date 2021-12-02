import java.util.*;

public class TenMinWalk {
  public static boolean isValid(char[] walk) {
    Map<Character, Integer> countMap = getCountMap(walk);
    return (walk.length == 10 && countMap.get('s') == countMap.get('n') && countMap.get('w') == countMap.get('e'));
  }

  private static Map<Character, Integer> getCountMap(char[] walk) {
    Map<Character, Integer> countMap = new HashMap<>();
    countMap.put('n', 0);
    countMap.put('s', 0);
    countMap.put('e', 0);
    countMap.put('w', 0);
    for (char c : walk) {
      countMap.put(c, countMap.get(c) + 1);
    }
    return countMap;
  }

  public static void main(String[] args) {
    System.out.println(isValid(new char[] { 'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }));
  }
}