package com.melt.test.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * @author melt
 * @create 2018/1/17 15:22
 */
public class OptionalTest {

    @Test
    public void option(){
        Person p = new Person("张三",20) ;
        Optional<Person> personOptional = Optional.ofNullable(null) ;
        System.out.println(personOptional.map(person -> "hi " + person.getName() +"!").orElse("hi XX !"));
    }
}
