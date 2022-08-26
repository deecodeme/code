package com.poc.code.ps.algos;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;

class TopKHashtagTest {

    @Test
    void ingest() {
        TreeSet<TopKHashtag.HashTagCount> treeSet = new TreeSet<>(Comparator.comparingInt(TopKHashtag.HashTagCount::getCount).reversed());
        treeSet.add(TopKHashtag.HashTagCount.of("tag_1", 5));
        treeSet.add(TopKHashtag.HashTagCount.of("tag_2", 2));
        treeSet.add(TopKHashtag.HashTagCount.of("tag_3", 3));

        treeSet.stream().forEach(e -> System.out.println(e));
        treeSet.remove(TopKHashtag.HashTagCount.of("tag_1", 2));
        System.out.println("After Delete.........");
        treeSet.stream().forEach(e -> System.out.println(e));
    }
}