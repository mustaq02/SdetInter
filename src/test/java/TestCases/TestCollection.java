package TestCases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestCollection {

    public static void main(String[] args) {
        ArrayList<String> collection = new ArrayList<String>();
        collection.add("Core java");
        collection.add("VNV Automation");
        collection.add("Full Stack");
        collection.add(".DOT net");
//    Iterator<String> iterator = collection.iterator();
//    //Returns an iterator over the elements
//        while (iterator.hasNext()) {
//        System.out.println( iterator.next());
        collection.remove(3);
        System.out.println(collection);
    }
}

