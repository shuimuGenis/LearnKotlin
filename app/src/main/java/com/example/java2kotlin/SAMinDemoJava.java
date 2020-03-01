package com.example.java2kotlin;

import android.util.Log;

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
    }
}
