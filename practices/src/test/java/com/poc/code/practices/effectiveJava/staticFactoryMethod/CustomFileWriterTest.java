package com.poc.code.practices.effectiveJava.staticFactoryMethod;

import com.poc.code.practices.effectiveJava.staticFactoryMethod.FileWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CustomFileWriterTest {

    @Test
    void bufferWriter() throws IOException {
        try (FileWriter fileWriter = FileWriter.bufferWriter("/Users/deepak.ku/Downloads/staticFactoryMethod.txt")) {
            fileWriter.append('a');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void byteEncodedWriter() throws IOException {
        try (FileWriter nonBufferedWriter = FileWriter.byteEncodedWriter("/Users/deepak.ku/Downloads/staticFactoryMethod.txt")) {
            nonBufferedWriter.append('b');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}