package com.poc.code.refactoring.templateMethod;

import java.io.File;

public abstract class DataMiner {
    protected void mine(File file) {
        openFile(file);
        Object data = extractAndParseData("filepath");
        System.out.println("Analysing data");
        System.out.println("sending report");
        closeFile(file);
    }

    protected abstract Object openFile(File file);

    protected abstract Object closeFile(File file);

    protected abstract Object extractAndParseData(String filePath);
}
