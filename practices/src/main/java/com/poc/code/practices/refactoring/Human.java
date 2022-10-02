package com.poc.code.practices.refactoring;

public class Human {
    private final Address address = new Address();
    private String name;
    private String age;

    public String getFullAddress() {
        StringBuilder result = new StringBuilder();
        return result
                .append(address.country)
                .append(", ")
                .append(address.city)
                .append(", ")
                .append(address.street)
                .append(", ")
                .append(address.house)
                .append(" ")
                .append(address.quarter).toString();
    }

}
