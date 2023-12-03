import java.util.*;
// package com.mycompany.os_ass3;
public class Process {
    public String name;
    public int size,num;
    public int take;
    ArrayList<Process>arr=new ArrayList();
    Scanner s=new Scanner(System.in);
    public Process(String name, int size, int take) {
        this.name = name;
        this.size = size;
        this.take=take;
    }
}

