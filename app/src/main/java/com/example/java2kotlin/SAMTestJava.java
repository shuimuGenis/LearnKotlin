package com.example.java2kotlin;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class SAMTestJava {
    /**
     * java 调用 kotlin写的lambda表达式时
     */
    public static void SAMTest3() {
        SAMinDemoKotlin saMinDemoKotlin = new SAMinDemoKotlin();
        //kotlin lambda表达式会被封装成Function对象
        Function0<Unit> function0 = new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return null;
            }
        };
        saMinDemoKotlin.addTask(function0);
        saMinDemoKotlin.addTask(function0);
        saMinDemoKotlin.romoveTask(function0);
        saMinDemoKotlin.romoveTask(function0);
    }
}
