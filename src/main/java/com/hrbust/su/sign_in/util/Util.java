package com.hrbust.su.sign_in.util;

import java.util.Random;

public class Util {
    private static final Integer CODELENGTH = 6;

    public static String generating6BitRandomNumbers(){
        Random random = new Random();
        StringBuilder sourceCode = new StringBuilder();
        for (int i = 0; i< CODELENGTH; i++)
        {
            sourceCode.append(random.nextInt(10));
        }
        return sourceCode.toString();
    }
}
