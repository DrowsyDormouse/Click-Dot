package com.example.song.clickdot;

/**
 * Created by Song on 2016-12-06.
 */

public class PlayerState {
    public static int money = 1000;
    public static int count = 0;
    public static int dotImageKinds = 0;
    public static int dotSwitch = 0;
    //public static boolean dotReset = false;

    public int DotKinds()
    {;
        int i;
        i = (int)(Math.random()*3);
        return i;
    }

    public int FinalDotKinds()
    {
        int i;
        i = (int)(Math.random()*2);
        return i;
    }
}
