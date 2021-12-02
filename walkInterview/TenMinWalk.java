import java.util.*;

public class TenMinWalk {
  private static final Character north = 'n';
  private static final Character south = 's';
  private static final Character east = 'e';
  private static final Character west = 'w';

  public static boolean isValid(char[] walk) {
    Map<Character, Integer> countMap = getCountMap(walk);
    return (walk.length == 10 && countMap.get(south) == countMap.get(north) && countMap.get(west) == countMap.get(east));
  }

  private static Map<Character, Integer> getCountMap(char[] walk) {
    Map<Character, Integer> countMap = new HashMap<>();
    countMap.put(north, 0);
    countMap.put(south, 0);
    countMap.put(east, 0);
    countMap.put(west, 0);
    for (char c : walk) {
      countMap.put(c, countMap.get(c) + 1);
    }
    return countMap;
  }

  public static void main(String[] args) {
    System.out.println(isValid(new char[] { 'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }));
  }
}