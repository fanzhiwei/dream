package net.fanzhiwei.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author fanzhiwei
 */
public class OptionalTest {
    @Test
    public void ofNullable() {
        String str = null;
        //Optional.of() 参数为null时会抛空指针异常
        //Optional.ofNullable() 参数为null时不抛异常
        int a = Optional.ofNullable(str).map(String::length).orElse(0);
        System.out.println(a);
    }
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
    }
    @Test
    public void test1() {
        Date d = new Date(1491146483763L);
        Date d2 = new Date(1491751283);
        System.out.println(d);
        System.out.println(d2);
    }
}
