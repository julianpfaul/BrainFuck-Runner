import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        Memory memory = new Memory();
        Pointer ptr = new Pointer(memory, (short) 0);

        String fileContents = FileUtilities.load("resources/main.bf");
        int currentInstructionIndex = 0;

        Scanner scanner = new Scanner(System.in);

        Stack<Integer> instructionStack = new Stack<>();

        boolean done = false;
        while (!done) {
            if (currentInstructionIndex >= fileContents.length()) break;

            if (fileContents.charAt(currentInstructionIndex) != '['
                    && fileContents.charAt(currentInstructionIndex) != ']')
                System.out.print("(" + fileContents.charAt(currentInstructionIndex) + "): ");

            switch (fileContents.charAt(currentInstructionIndex)) {
                case '>' -> {
                    ptr.move((short) 1);
                    currentInstructionIndex += 1;
                }
                case '<' -> {
                    ptr.move((short) -1);
                    currentInstructionIndex += 1;
                }
                case '+' -> {
                    ptr.increment();
                    currentInstructionIndex += 1;
                }
                case '-' -> {
                    ptr.decrement();
                    currentInstructionIndex += 1;
                }
                case '.' -> {
                    ptr.print();
                    currentInstructionIndex += 1;
                }
                case ',' -> {
                    ptr.set(scanner.nextByte());
                    currentInstructionIndex += 1;
                }
                case '[' -> {
                    if(ptr.get() == 0){
                        int openingCounter = 1;
                        int closingCounter = 0;

                        int index;
                        for (index = currentInstructionIndex + 1; openingCounter - closingCounter != 0; index++){
                            if(fileContents.charAt(index) == '[') openingCounter += 1;
                            if(fileContents.charAt(index) == ']') closingCounter += 1;
                        }
                        currentInstructionIndex = index;
                    }else {
                        instructionStack.push(currentInstructionIndex);
                        currentInstructionIndex += 1;
                    }
                }
                case ']' -> {
                    if (ptr.get() == 0) {
                        instructionStack.pop();
                    } else {
                        currentInstructionIndex = instructionStack.peek();
                    }
                    currentInstructionIndex += 1;
                }
                default -> {
                    done = true;
                }
            }

            System.out.print("\n");
        }

        System.out.print("\n");

        memory.print();
    }
}