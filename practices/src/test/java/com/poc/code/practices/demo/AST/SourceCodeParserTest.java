package com.poc.code.practices.demo.AST;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.BadLocationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SourceCodeParserTest {

    @Test
    void readFileToString() throws IOException {
        String filePath = "/src/main/java/com/poc/code/practices/demo/AST/RemoveUnnecessaryDeclaration.java";
        String fileAbsolutePath = System.getProperty("user.dir") + filePath;
        String fileStr = SourceCodeParser.readFileToString(fileAbsolutePath);
    }

    @Test
    void parse() throws IOException {
        String filePath = "/src/main/java/com/poc/code/practices/demo/AST/RemoveUnnecessaryDeclaration.java";
        String fileAbsolutePath = System.getProperty("user.dir") + filePath;
        CompilationUnit compilationUnit = SourceCodeParser.parse(fileAbsolutePath);
    }

    @Test
    void addComment() throws IOException, JavaModelException, BadLocationException {
        String filePath = "/src/main/java/com/poc/code/practices/demo/AST/RemoveUnnecessaryDeclaration.java";
        String fileAbsolutePath = System.getProperty("user.dir") + filePath;
        SourceCodeParser.addComment(fileAbsolutePath);
    }
}