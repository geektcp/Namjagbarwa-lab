package com.geektcp.alpha.agent.example.bean;

/**
 * @author haiyang.tang on 12.04 004 11:48:14.
 */
public class Person {
    private String name;
    private int ID;
    public String speed;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public Person(String name,int ID){
        this.name = name;
        this.ID = ID;
    }
    public void Speak(){
        System.out.println("Hello! "+"My name is "+name);
    }
    public void run(String speed){
        System.out.println("I can run " + speed+" KM!!!");
    }

    public void set(){
        ID= 10;
        System.out.println("I can run ID:" + ID);
    }

}
