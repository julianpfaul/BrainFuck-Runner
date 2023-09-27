package com.jp.brainfuck;

import com.jp.brainfuck.memory.Memory;
import com.jp.brainfuck.memory.Pointer;

import java.util.Scanner;
import java.util.Stack;

public class Interpreter {

    public static void run(String BFSourceCode){
        Memory memory = new Memory();
        Pointer ptr = new Pointer(memory, (short) 0);

        int currentInstructionIndex = 0;

        Scanner scanner = new Scanner(System.in);

        Stack<Integer> instructionStack = new Stack<>();

        boolean done = false;
        while (!done) {
            if (currentInstructionIndex >= BFSourceCode.length()) break;

//            if (fileContents.charAt(currentInstructionIndex) != '['
//                    && fileContents.charAt(currentInstructionIndex) != ']')
//                System.out.print("(" + fileContents.charAt(currentInstructionIndex) + "): ");

            switch (BFSourceCode.charAt(currentInstructionIndex)) {
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
                    System.out.print("\n");
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
                            if(BFSourceCode.charAt(index) == '[') openingCounter += 1;
                            if(BFSourceCode.charAt(index) == ']') closingCounter += 1;
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
        }

        memory.print();
    }

}
