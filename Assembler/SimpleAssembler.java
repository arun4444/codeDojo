package CodeDojo.Assembler;

import java.util.*;

public class SimpleAssembler {
  public static Map<String, Integer> interpret(String[] program) {
    Map<String, Integer> registers = new HashMap<String, Integer>();
    int pc = 0;
    while (pc < program.length && pc > -1) {
      String[] currentInst = program[pc].split(" ");
      switch (currentInst[0]) {
      case "mov":
        int moveee = getRegisterOrConstant(currentInst[2], registers);
        registers.put(currentInst[1], moveee);
        pc++;
        break;
      case "inc":
        int currentIncVal = registers.get(currentInst[1]);
        registers.put(currentInst[1], currentIncVal + 1);
        pc++;
        break;
      case "dec":
        int currentDecVal = registers.get(currentInst[1]);
        registers.put(currentInst[1], currentDecVal - 1);
        pc++;
        break;
      case "jnz":
        int test = getRegisterOrConstant(currentInst[1], registers);
        int jumpLoc = getRegisterOrConstant(currentInst[2], registers);
        if (test != 0) {
          pc = pc + jumpLoc;
        } else {
          pc++;
        }
        break;
      default:
        break;
      }
    }

    return registers;
  }

  private static int getRegisterOrConstant(String operand, Map<String, Integer> registers) {
    try {
      int parsedOperand = Integer.parseInt(operand);
      return parsedOperand;
    } catch (NumberFormatException ne) {
      return registers.get(operand);
    }
  }

  public static void main(String[] args) {
    String[] program = new String[] { "mov a -10", "mov b a", "inc a", "dec b", "jnz a -2" };
    Map<String, Integer> out = new HashMap<String, Integer>();
    out.put("a", 0);
    out.put("b", -20);
    System.out.println(interpret(program));
  }

}
