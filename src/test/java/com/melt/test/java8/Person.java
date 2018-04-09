package com.melt.test.java8;

import java.util.function.Supplier;

/**
 * @author melt
 * @create 2018/1/17 14:37
 */
public class Person {
    private String name;
    private int age ;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    /**
     * 构造
     * @param supplier
     * @return
     */
    public static Person create(final Supplier<Person> supplier){
        return  supplier.get() ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void printName(final Person p){
        System.out.println(p.getName());
    }

    public void printName2(final Person p){
        System.out.println(p.getName());
    }

    public void printName3(){
        System.out.println(this.getName());
    }
}
