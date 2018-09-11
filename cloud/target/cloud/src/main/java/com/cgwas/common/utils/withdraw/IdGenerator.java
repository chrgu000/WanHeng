package com.cgwas.common.utils.withdraw;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class IdGenerator {


    private static ThreadLocal<Random> threadLocal = new ThreadLocal<Random>();

    public static String getPrimaryKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Random getRandom() {
        Random r = threadLocal.get();
        if (r == null) {
            r = new Random();
            threadLocal.set(r);
        }
        return r;
    }

    public static String getTradeNum() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String str = "" + System.currentTimeMillis();
        return str;
    }

    public static String getOrdersNum() {
        Random random = getRandom();
        String s = String.format("%04d", random.nextInt(10000));
        String str = System.currentTimeMillis() + s;
        return str;
    }

    public static String getYURREF() {
        Random random = getRandom();
        String s = String.format("%04d", random.nextInt(10000));
        String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + System.currentTimeMillis() + s;
        return str;
    }

    public static String getOrdersNum2() {
        String str = "" + System.currentTimeMillis();
        return str;
    }

    /**
     * 获取list最小值
     *
     * @param ins
     * @return
     */
    public static Double getMin(List<Double> ins) {
        Object[] objs = ins.toArray();
        Arrays.sort(objs);
        return Double.valueOf(String.valueOf(objs[0]));
    }
}
