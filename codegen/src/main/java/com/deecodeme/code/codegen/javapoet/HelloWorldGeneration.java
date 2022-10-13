package com.deecodeme.code.codegen.javapoet;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;

public class HelloWorldGeneration {
    private final Logger log = LoggerFactory.getLogger(HelloWorldGeneration.class.getName());

    public void generateHelloWorldClass() {
        MethodSpec mainMethodSpec = MethodSpec.methodBuilder("mainMethod")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        ParameterSpec parameterSpec = ParameterSpec.builder(String.class, "guest").build();

        MethodSpec helloMethodSpec = MethodSpec.methodBuilder("hello")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addParameter(parameterSpec)
                .addStatement("return $S", "Hi there!")
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(mainMethodSpec)
                .addMethod(helloMethodSpec)
                .build();

        try {
            JavaFile.builder("com.deecodeme.code.codegen.javapoet", typeSpec)
                    .build().writeToPath(Path.of(System.getProperty("user.dir") + "/src/main/java"));
        } catch (IOException ex) {
            log.error("Error while writing the type to file: {}", ex.getMessage());
        }
    }
}
