package com.example.learngradle;

/**
 * @author shuimu{lwp}
 * @time 2021/4/27 16:44
 * @desc
 */
public class TestClass {
    public void test(){
        ShuiInterface temp= new ShuiInterface(){

            @Override
            public void shui() {

            }

            public void call(){}
        };
        temp.shui();
        //temp.call();
    }
}
