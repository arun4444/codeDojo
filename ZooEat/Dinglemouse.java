package CodeDojo.ZooEat;

import java.util.*;

public class Dinglemouse {

  private static final Map<String, Set<String>> CHOW_MAP = getChowMap();
  private static Map<String, Set<String>> getChowMap() {
    final Map<String, Set<String>> chowMap = new HashMap<>();
    chowMap.put("antelope", Set.of("grass"));
    chowMap.put("big-fish", Set.of("little-fish"));
    chowMap.put("bug", Set.of("leaves"));
    chowMap.put("bear", Set.of("bug", "chicken", "cow", "leaves", "sheep", "big-fish"));
    chowMap.put("chicken", Set.of("bug"));
    chowMap.put("cow", Set.of("grass"));
    chowMap.put("fox", Set.of("chicken", "sheep"));
    chowMap.put("giraffe", Set.of("leaves"));
    chowMap.put("lion", Set.of("antelope", "cow"));
    chowMap.put("panda", Set.of("leaves"));
    chowMap.put("sheep", Set.of("grass"));
    return chowMap;
  }

  private static boolean canEat(String eater, String eatee){
    Set<String> menu = CHOW_MAP.get(eater);
    if (menu != null && menu.contains(eatee)){
      return true;
    }
    return false;
  }

  public static String[] whoEatsWho(final String zoo) {
    final LinkedList<String> zooState = new LinkedList<>();
    zooState.addAll(Arrays.asList(zoo.split(",")));
    final List<String> messages = new ArrayList<>();

    ListIterator<String> zooIter = zooState.listIterator();
    while (zooIter.hasNext()) {
      String previous = null;
      String next = null;
      String current = null;
      if (zooIter.hasPrevious()) {
        previous = zooIter.previous();
        zooIter.next();
      }
      current = zooIter.next();
      if (zooIter.hasNext()) {
        next = zooIter.next();
        zooIter.previous();
      }
      if(previous != null && canEat(current, previous)){
        zooIter.previous();
        zooIter.previous();
        zooIter.remove();
        messages.add(current+" eats "+previous);
        zooIter = zooState.listIterator();
      } else if (next != null && canEat(current, next)){
        zooIter.next();
        zooIter.remove();
        messages.add(current+" eats "+next);
        zooIter = zooState.listIterator();
      }
    }
    String[] returner = new String[2 + messages.size()];
    returner[0] = zoo;
    for (int i = 0; i < messages.size(); i++) {
      returner[i+1] = messages.get(i);
    }
    returner[returner.length-1] = zooState.toString().replace("[", "")
              .replace("]", "")
              .replace(" ", "");
    return returner;
  }

  public static void main(String[] args) {
    final String input = "grass,grass,cow,leaves,bug,big-fish,leaves,bear";
    System.out.println(Arrays.toString(whoEatsWho(input)));
  }

}
