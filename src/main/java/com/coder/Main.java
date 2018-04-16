package com.coder;

public class Main {

    public Main(){
        System.out.println(4);
    }

    {
        System.out.println(2);
    }

    static{
        System.out.println(1);
    }



    public static void main(String[] args) {
        System.out.println(3);
        new Main();
    }
}
