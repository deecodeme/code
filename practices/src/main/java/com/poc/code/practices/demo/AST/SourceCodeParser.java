package com.poc.code.practices.demo.AST;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class SourceCodeParser {
    public static CompilationUnit parse(final String filePath) throws IOException {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setSource(readFileToString(filePath).toCharArray());
        return (CompilationUnit) parser.createAST(null /*IProgressMonitor*/);
    }

    public static void addComment(final String filePath) throws IOException, JavaModelException, BadLocationException {
        CompilationUnit astRoot = parse(filePath);
        ICompilationUnit unit = (ICompilationUnit) astRoot.getJavaElement();
        AST ast = astRoot.getAST();
        ASTRewrite astRewrite = ASTRewrite.create(ast);

        // get insertion position
        TypeDeclaration typeDeclaration1 = (TypeDeclaration) astRoot.types().get(0);
        MethodDeclaration methodDeclaration1 = (MethodDeclaration) typeDeclaration1.getMethods()[0];
        Block block = methodDeclaration1.getBody();

        ListRewrite listRewrite = astRewrite.getListRewrite(block, Block.STATEMENTS_PROPERTY);
        Statement placeHolder = (Statement) astRewrite.createStringPlaceholder("//comment by AST", ASTNode.EMPTY_STATEMENT);
        listRewrite.insertFirst(placeHolder, null);

        TextEdit textEdits = astRewrite.rewriteAST();
        Document document = new Document(unit.getSource());
        textEdits.apply(document);

        // this is the code for adding statements
        unit.getBuffer().setContents(document.get());

        log.info("Done");
    }

    public static String readFileToString(final String filePath) throws IOException {
        StringBuilder fileStr = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            bufferedReader.lines().forEach(line -> fileStr.append(line));
        } catch (IOException ex) {
            log.error("Error while parsing file to string");
            throw ex;
        }
        return fileStr.toString();
    }

}
