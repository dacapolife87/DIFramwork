package org.hjjang.bean.collection;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCollection {

    static Hashtable<String,Object> initBeanTable = new Hashtable<String,Object>();

    static Hashtable<String,Object> injectedBeanTable = new Hashtable<String,Object>();

    public static void registBean(Class bean){
        initBeanTable.put(bean.getName(),bean);
    }

    public static Object getBean(String beanName){
        return initBeanTable.get(beanName);
    }

    public static int size(){
        return initBeanTable.size();
    }

    public static List<Object> getBeanList(){
        return initBeanTable.values().stream().collect(Collectors.toList());
    }

    public static void registInjectBean(Class bean){
        injectedBeanTable.put(bean.getName(),bean);
    }

    public static Object getInjectBean(String beanName){
        return injectedBeanTable.get(beanName);
    }
}
