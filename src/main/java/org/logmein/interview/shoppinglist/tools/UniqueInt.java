package org.logmein.interview.shoppinglist.tools;

public class UniqueInt {

    private static int id = 0;

    public static synchronized int getNext() {
        return id++;
    }
}
