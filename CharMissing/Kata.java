public class Kata
{
  public static char findMissingLetter(char[] array)
  {
    int prev = (int) array[0];
    for (int i = 1; i < array.length; i++) {
        int current = (int) array[i];
        if(prev + 1 != current){
            return ((char) (prev + 1));
        }
        prev = current;
    }
    return ' ';
  }

  public static void main(String[] args){
    System.out.println(Kata.findMissingLetter(new char[] { 'A','B','C','D','F' }));
  }
}
