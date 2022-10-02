package com.poc.code.practices.refactoring;

import java.util.ArrayList;

public class InlineMethod {
    private int numberOfLateDeliveries;

    int getRating() {
        return numberOfLateDeliveries > 5 ? 2 : 1;
    }

    int getBadRating() {
        return numberOfLateDeliveries > 5 ? 2 : 1;
    }

    public ArrayList method() {
        String[] strings = {"a","b","c"};
        ArrayList list1 = new ArrayList();
        for (int i = 0; i< strings.length; i++)
        {
            list1.add(strings[i]);}
        ArrayList list= list1;
        return list;
    }

}
