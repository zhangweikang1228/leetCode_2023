package com.kang;

public class Main {

    public static void main(String[] args) {
        Dog aDog = new Dog("Max");
        Dog oldDog = aDog;

        // we pass the object to foo
        foo(aDog);
        // aDog variable is still pointing to the "Max" dog when foo(...) returns
        System.out.println(aDog.getName().equals("Max")); // true
        System.out.println(aDog.getName().equals("Fifi")); // false
        System.out.println(aDog == oldDog); // true
    }

    public static void foo(Dog d) {
        System.out.println("fooo 1 : " + d.getName().equals("Max")); // true
        // change d inside of foo() to point to a new Dog instance "Fifi"
        //d = new Dog("Fifi");
        d.setName("Fifi");
        System.out.println("fooo 2 : " + d.getName().equals("Fifi")); // true
    }
}


class Dog{
    private String name;
    Dog(){

    }
    Dog(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}