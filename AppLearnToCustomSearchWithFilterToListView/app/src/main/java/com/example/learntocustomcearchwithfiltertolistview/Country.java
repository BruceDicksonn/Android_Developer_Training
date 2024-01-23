package com.example.learntocustomcearchwithfiltertolistview;

public class Country {
    int id;
    int res;
    String name;

    public Country(int id, int res, String name) {
        this.id = id;
        this.res = res;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
