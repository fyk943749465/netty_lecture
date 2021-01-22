package com.shengsiyuan.nio.book.crazymakercircle.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;

public class ConfigProperties {

    private String propertiesName = "";
    private Properties properties = new Properties();

    public ConfigProperties() {

    }

    public ConfigProperties(String fileName) {
        this.propertiesName = fileName;
    }

    protected void loadFromFile() {
        InputStream in = null;
        InputStreamReader ireader = null;
        try {
            String filePath = IOUtil.getResourcePath(propertiesName);
            in = new FileInputStream(filePath);
            // 解决非UTF-8编码的配置文件时，出现的中文乱码问题
            ireader = new InputStreamReader(in, "utf-8");
            properties.load(ireader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(ireader);
        }
    }

    public String readProperty(String key) {
        String value = "";
        value = properties.getProperty(key);
        return value;
    }

    public String getValue(String key) {
        return readProperty(key);
    }

    public int getIntValue(String key) {
        return Integer.parseInt((readProperty(key)));
    }

    public static ConfigProperties loadFromFile(Class aClass)
            throws IllegalAccessException
    {

        ConfigProperties propertiesUtil = null;


        return propertiesUtil;
    }

    public static void loadAnnotations(Class aClass)
    {

        ConfigProperties configProperties = null;
        try
        {
            configProperties = loadFromFile(aClass);


            if (null == configProperties) return;

            Field[] fields = aClass.getDeclaredFields();

        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
