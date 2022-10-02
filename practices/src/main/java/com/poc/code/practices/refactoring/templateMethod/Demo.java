package com.poc.code.practices.refactoring.templateMethod;

import java.io.File;

public class Demo {
    public static void main(String[] args) {
        DataMiner docDataMiner = new DocDataMiner();
        docDataMiner.mine(new File(""));

        DataMiner excelDataMinor = new ExcelDataMinor();
        excelDataMinor.mine(new File(""));

        DataMiner pdfDataMinor = new PdfDataMinor();
        pdfDataMinor.mine(new File(""));
        Object o = new Object();
    }
}
