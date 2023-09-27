package com.jp;

import com.jp.utils.FileUtilities;
import com.jp.brainfuck.Interpreter;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        try {
            Interpreter.run(FileUtilities.load("resources/main.bf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}