package CodeDojo.Direction;

import java.util.Stack;

public class DirReduction {
    private static boolean isComplimentDirection(String dir1, String dir2){
      if ((dir1 == "NORTH" && dir2 == "SOUTH") || (dir1 == "SOUTH" && dir2 == "NORTH")){
        return true;
      }
      if ((dir1 == "EAST" && dir2 == "WEST") || (dir1 == "WEST" && dir2 == "EAST")){
        return true;
      }
      return false;
    }
  
    public static String[] dirReduc(String[] arr) {
        Stack<String> result = new Stack<String>();
        for (int i = 0; i < arr.length; i++) {
          String currentDir = arr[i];
          if (result.empty()){
            result.push(currentDir);
            continue;
          }
          String topDir = result.peek();
          while (isComplimentDirection(currentDir, topDir)){
            result.pop();
            currentDir = null;
            if (result.size() > 1){
              currentDir = result.pop();
              topDir = result.peek();
            }
          }
          if (currentDir != null){
            result.push(currentDir);
          }
        }
        return result.toArray(new String[result.size()]);
    }
}