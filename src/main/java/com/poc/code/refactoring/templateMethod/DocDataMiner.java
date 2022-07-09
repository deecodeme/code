package com.poc.code.refactoring.templateMethod;

import java.io.File;

public class DocDataMiner extends DataMiner {
    @Override
    protected Object openFile(File file) {
        System.out.println("Opening file");
        return null;
    }

    @Override
    protected Object closeFile(File file) {
        System.out.println("Closing file");
        return null;
    }

    @Override
    protected Object extractAndParseData(String filePath) {
        System.out.println("Extracting and parsing Doc data");
        return null;
    }
}
