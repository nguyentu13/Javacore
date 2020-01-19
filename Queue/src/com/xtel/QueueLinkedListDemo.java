package com.xtel;

import java.util.LinkedList;
import java.util.Queue;

public class QueueLinkedListDemo {
    public static void main(String[] args) {

        Queue<String> names = new LinkedList<String>();
 
        names.offer("E");
        names.offer("A");
        names.offer("M");
 
        names.add("G");
        names.add("B");
 
        while (true) {

            String name = names.poll();
            if (name == null) {
                break;
            }
            System.out.println("Name=" + name);
        }
 
    }
}
