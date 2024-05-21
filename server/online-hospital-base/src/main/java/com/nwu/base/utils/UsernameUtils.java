package com.nwu.base.utils;

import java.util.Random;

/**
 * @version 1.0
 * @Author lcy
 * @Description 随机生成用户昵称
 * @DateTime 2024/3/22 21:47
 **/
public class UsernameUtils {
    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return "用户" + val;
    }
}

