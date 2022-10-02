package com.poc.code.practices.design.SubDirectories;

import com.poc.code.practices.design.SubDirectories.Directory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DirectoryTest {
    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getSubDirTest() {
        Directory d = new Directory("/", null);

        List<String> want = new ArrayList<>();
        Assertions.assertTrue(want.equals(d.getSubDir()));

        d.addSubDir("e1");
        want.add("e1");
//        Assert.assertEquals(want.toArray(), d.getSubDir().toArray());

        d.addSubDir("d1");
        want.add("d1");
        //d.getSubDir().stream().forEach(e -> System.out.println(e));
        Assertions.assertTrue(want.equals(d.getSubDir()));

        d.addSubDir("c1");
        want.add("c1");

        d.addSubDir("b1");
        want.add("b1");


        d.addSubDir("a1");
        want.add("a1");
    }
}