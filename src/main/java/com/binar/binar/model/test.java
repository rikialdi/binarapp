package com.binar.binar.model;



import java.util.HashSet;
import java.util.Set;

public class test {
    public  static void main(String[] args){
        Set<test1> set = new HashSet<test1>();
        test1 a = new test1();
        a.setId(1L);
        set.add(a);

        test1 b = new test1();
        b.setId(2L);
        set.add(b);

        test1 c = new test1();
        c.setId(3L);
        set.add(c);

        Set<test1> set2 = new HashSet<test1>();
//        set2.add("four");
//
//        set2.addAll(set);
        for(test1 as : set){
            set2.add(as);
            System.out.println("="+as.getId());
        }

        for(test1 aa : set2){
            System.out.println("b="+aa.getId());
        }
    }

}
