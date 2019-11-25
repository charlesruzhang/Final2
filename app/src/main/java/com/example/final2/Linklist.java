package com.example.final2;

/**
 * this class is for activity 7, using for storing data
 */

public class Linklist {
    class Item {
        boolean value;
        Item next;
        public Item(Boolean setValue, Item setNext) {
            value = setValue;
            next = setNext;
        }
    }

    protected Item start;
    protected int currentSize;

    public Item getItem(int index) {
        if (index < 0 || index >= currentSize) {
            return null;
        }
        int currentIndex = 0;
        for (Item current = start; current != null; current = current.next) {
            if (index == currentIndex) {
                return current;
            }
            currentIndex++;
        }
        return null;
    }
    public void add(int index, boolean toAdd) {
        if (index == 0) {
            start = new Item(toAdd, start);
            currentSize++;
            return;
        }
        if (index > currentSize || index < 0) {
            return;
        }
        Item pre = getItem(index - 1);
        Item next = getItem(index);
        Item add = new Item(toAdd, next);
        pre.next = add;
        currentSize++;

    }
}
