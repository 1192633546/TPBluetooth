package com.tpnet.tpbluetooth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void cc() throws Exception {
        byte a = 0x53;
        char c = (char) a;
        System.out.println(c);
    }

    @Test
    public void concat() {
        String a = "a";
        String b = "b";
        System.out.println(a.concat(b));
    }

    @Test
    public void copy() {
        byte[] b1 = {0, 1, 2, 3, 4, 5, 6};
        byte[] b2 = new byte[b1.length - 2];
        System.arraycopy(b1, 2, b2, 0, b2.length);


        for (int i = 0; i < b2.length; i++) {
            System.out.println(b2[i]);
        }
    }

}