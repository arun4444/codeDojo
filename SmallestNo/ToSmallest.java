package CodeDojo.SmallestNo;

public class ToSmallest {

  public static long insertAt(String s, int from, int to) {
    char m = s.charAt(from);
    s = s.substring(0, from) + s.substring(from + 1);
    s = s.substring(0, to) + m + s.substring(to);
    return Long.parseLong(s);
  }

  public static long[] smallest(Long n) {
    String inputStr = Long.toString(n);
    long min = n;
    long takeIndex = 0;
    long placeIndex = 0;
    for (int i = 0; i < inputStr.length(); i++) {
      for (int j = 0; j < inputStr.length(); j++) {
        if ((i != j) && (insertAt(inputStr, i, j) < min)) {
          min = insertAt(inputStr, i, j);
          takeIndex = i;
          placeIndex = j;
        }
      }
    }
    long[] result = new long[3];
    result[0] = min;
    result[1] = no1;
    result[2] = no2;
    return result;
  }
}
