package com.poc.code.practices.effectiveJava.memoryManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class PreferTryWithResourceOverFinally {
    private static Logger log = LoggerFactory.getLogger(PreferTryWithResourceOverFinally.class);

    /**
     * Looks clean with autocloseable and try with resource
     * Preferable
     *
     * @param path
     */
    public void readFileTryWithResources(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            bufferedReader.lines().forEach(line -> log.info(line));
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Error reading file: {}", e.getMessage());
        }
    }

    /**
     * Looks clean with autocloseable and try with multiple resource. Preferable
     *
     * @param srcPath
     * @param tarPath
     */
    public void copyFileTryWithResources(String srcPath, String tarPath) {
        int bufferSize = 100;
        try (InputStream in = new FileInputStream(srcPath);
             OutputStream out = new FileOutputStream(tarPath)) {
            byte[] buf = new byte[bufferSize];
            int n;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Error reading file: {}", e.getMessage());
        }
    }

    /**
     * finally looks nasty when dealing with more than one resource
     *
     * @param srcPath
     * @param tarPath
     */
    public void copyFileWithTryFinally(String srcPath, String tarPath) {
        int bufferSize = 100;
        try {
            InputStream inputStream = new FileInputStream(srcPath);
            try {
                OutputStream outputStream = new FileOutputStream(tarPath);
                try {
                    byte[] buf = new byte[bufferSize];
                    int n;
                    while ((n = inputStream.read(buf)) > 0) {
                        outputStream.write(buf, 0, n);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                // An exception can be thrown here if the stream is already close because of a hardware failure
                // In that case, this exception would obliterate the first exception if any.
                inputStream.close();
            }
        } catch (IOException ex) {
            log.error("Error while read and write : {}", ex.getMessage());
        }
    }
}
