package com.example.MyWatchList.AppEntry;

public class Pair<A, B>{
    private A first = null;
    private B second = null;

    public Pair(){}

    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "(" + first + ", " + second + ")";
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

