package com.melt.test.ConSale;

/**
 * 客户
 * @author melt
 * @create 2018/1/25 16:40
 */
public class Customer {

    private String name ;

    private int amount ;

    public Customer(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

