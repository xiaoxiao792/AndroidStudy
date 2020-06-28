package com.shaobing.runner.Tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockUtils {

    private static String getImgUrl() {
        List<String> imgs = new ArrayList<>();
        return imgs.get(new Random().nextInt(imgs.size()));
    }

    private static String getVideoUrl() {
        List<String> imgs = new ArrayList<>();
        imgs.add("http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8");
        return imgs.get(new Random().nextInt(imgs.size()));
    }

    public static <T> T mockData(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            setValue(t, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T mockData(Class<T> clazz,String url) throws IllegalAccessException {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            Object value = url;
            if (value != null) {
                field.setAccessible(true);
                field.set(t, value);
            }
        }

        return t;
    }

    public static <T> List<T> mockList(Class<T> clazz) {
        return mockList(clazz, 10);
    }

    public static <T> List<T> mockList(Class<T> clazz, int count) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            T t = mockData(clazz);
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }

    private static <T> void setValue(Object object, Class<T> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            Object value = getFieldMockValue(field);
            if (value != null) {
                field.setAccessible(true);
                field.set(object, value);
            }
        }
    }

    /**
     * 模拟基础类型数据
     */
    private static Object getFieldMockValue(Field field) {
        Class clazzType = field.getType();
        Object value = null;
        if (clazzType == int.class || clazzType == Integer.class) {
            value = new Random().nextInt(100);
        } else if (clazzType == long.class || clazzType == Long.class) {
            value = Math.abs(new Random().nextLong() % 10000);
        } else if (clazzType == float.class || clazzType == Float.class) {
            value = new Random().nextFloat();
        } else if (clazzType == double.class || clazzType == Double.class) {
            value = new Random().nextDouble();
        } else if (clazzType == String.class) {
            String name = field.getName();
            // TODO: 可添加修改更多规则
            if (name.toLowerCase().contains("img") || name.toLowerCase().contains("url")) {
                value = getImgUrl();
            } else if (name.toLowerCase().contains("video")) {
                value = getVideoUrl();
            } else {
                value = name + "_" + new Random().nextInt(100);
            }
        } else if (clazzType == boolean.class || clazzType == Boolean.class) {
            value = new Random().nextBoolean();
        }
        return value;
    }

}
