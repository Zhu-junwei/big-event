package com.zjw.bigevent.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 朱俊伟
 * @since 2024/03/16 1:00
 */
class Md5UtilTest {

    @Test
    public void testGetMD5String() {
        String string = Md5Util.getMD5String("123456");
        System.out.println("string = " + string);
    }

}