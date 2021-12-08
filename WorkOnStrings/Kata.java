import java.util.*;

class Kata {

    public static String workOnStrings(String a, String b) {
        final Map<Character, Integer> aFreq = getFreqMap(a);
        final Map<Character, Integer> bFreq = getFreqMap(b);
        return toggleString(a, bFreq) + toggleString(b, aFreq);
    }

    private static Character swapCase(Character c) {
        return Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
    }

    private static Map<Character, Integer> getFreqMap(String str) {
        final Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            final Character c = Character.toLowerCase(str.charAt(i));
            final Integer count = freq.containsKey(c) ? freq.get(c) : 0;
            freq.put(c, count + 1);
        }
        return freq;
    }

    private static String toggleString(String s, Map<Character, Integer> freqMap) {
        final char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            final Character cL = Character.toLowerCase(c);
            final Integer count = freqMap.containsKey(cL) ? freqMap.get(cL) : 0;
            if (count % 2 != 0) {
                c = swapCase(c);
            }
            result[i] = c;
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(Kata.workOnStrings("abcdeFgtrzw", "defgGgfhjkwqe").equals("abcDeFGtrzWDEFGgGFhjkWqE"));
    }

}