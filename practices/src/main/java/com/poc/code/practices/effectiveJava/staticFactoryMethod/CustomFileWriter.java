package com.poc.code.practices.effectiveJava.staticFactoryMethod;

import java.io.*;

class CustomFileWriter implements FileWriter {
    private Writer writer;

    CustomFileWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public Writer append(char c) throws IOException {
        return this.writer.append(c);
    }

    @Override
    public void close() throws Exception {
        this.writer.close();
    }
}
