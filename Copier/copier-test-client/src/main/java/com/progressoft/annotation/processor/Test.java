package com.progressoft.annotation.processor;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by u343 on 10/16/16.
 */
public class Test {

    public static class TestCollection{
        public static class SomeObject{

        }
        public List<SomeObject> objects;
        public Map<String,String> map;

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }
    public static void main(String[] args) {

        Map<String, String> strings=new HashMap<>();
        for (java.util.Map.Entry entry:strings.entrySet()) {
            entry.getKey();
            entry.getValue();
        }

        TestCollection tc1=new TestCollection();
        tc1.objects=new ArrayList<TestCollection.SomeObject>(){{
            add(new TestCollection.SomeObject());
            add(new TestCollection.SomeObject());
            add(new TestCollection.SomeObject());
            add(new TestCollection.SomeObject());
        }};

        tc1.setMap(new HashMap<>());



//        TestCollection tc2=new TestCollection();
//        tc2.objects=new ArrayList<>();
//        for (String s :tc1.objects) {
//            tc2.objects.add(s);
//        }
//
//        tc2.objects.clear();
//        System.out.println(tc1.objects);
//        System.out.println(tc2.objects);

    }
}
