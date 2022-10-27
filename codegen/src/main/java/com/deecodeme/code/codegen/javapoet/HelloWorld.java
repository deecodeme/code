package com.deecodeme.code.codegen.javapoet;

import java.lang.String;
import java.lang.System;

public final class HelloWorld {
  public static void mainMethod(String[] args) {
    System.out.println("Hello, JavaPoet!");
  }

  public String hello(String guest) {
    return "Hi there!";
  }
}
