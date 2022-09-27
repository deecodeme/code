package com.poc.code.effectiveJava.staticFactoryMethod;

import java.io.*;

public interface FileWriter extends AutoCloseable {
    Writer append(char c) throws IOException;

    static FileWriter bufferWriter(String filePath) throws IOException {
        return new CustomFileWriter(new BufferedWriter(new java.io.FileWriter(filePath)));
    }

    static FileWriter byteEncodedWriter(String filePath) throws FileNotFoundException {
        return new CustomFileWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
    }
}