package com.melt.test.java8;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author melt
 * @create 2018/1/17 14:41
 */
public class Test01 {

    private List<Person> list = null;

    @Before
    public void setUp() throws Exception {
        list = Arrays.asList(new Person("张三",20),
                new Person("李四",12),
                new Person("王五",30),
                new Person("钱六",10),
                new Person("张三",60)
                ) ;

    }

    @Test
    public void filter(){
        list = list.stream().filter(p -> (p.getAge() > 18)).collect(Collectors.toList()) ;
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void map(){
        int sum = list.stream().filter(p -> (p.getAge() > 18)).mapToInt(Person::getAge).sum() ;
        System.out.println(sum);
    }

    @Test
    public void group(){
        final Map<String, List<Person>> collect = list.stream().collect(Collectors.groupingBy(Person::getName));
        System.out.println(collect);
    }

    @Test
    public void create(){
        Person person = Person.create(Person::new) ;
        person.setName("张三");
    }

    @Test
    public void forach(){
        list.forEach(Person::printName);
        list.forEach(Person::printName3);

    }
}
