package com.example.java2kotlin;

import android.util.Log;


import com.example.kotlin.Example;

import java.util.ArrayList;

public class SAMinDemoJava {
    private ArrayList<Runnable> runnableList = new ArrayList<>();

    public void addTask(Runnable run) {
        runnableList.add(run);
        Log.i("zui", "runnable list count by add " + runnableList.toString());
    }

    public void removeTask(Runnable run) {
        runnableList.remove(run);
        Log.i("zui", "runnable list count by remove " + runnableList.toString());
        Example.cxIsNotEmpty("wojius");
    }

    public static void main(String[] args) {
        Object temp=":";
        System.out.println(""+temp.getClass().getName());
        temp=1;
        System.out.println(""+temp.getClass().getName());
    }
}

