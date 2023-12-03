import java.util.*;
// package com.mycompany.os_ass3;
public class partition {
    public String name;
    public int size,num;
    // boolean state;
    public String nullprocess;//intial External fregmant
    ArrayList<partition>arr=new ArrayList();
    Scanner s=new Scanner(System.in);
    public partition() {
    }
    public partition(String name, int size, String process_name){
        this.name = name;
        this.size = size;
        // this.state=b;
        this.nullprocess=process_name;
    }
}

