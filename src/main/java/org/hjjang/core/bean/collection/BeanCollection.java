package org.hjjang.core.bean.collection;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCollection {

    static Hashtable<String,Object> initBeanTable = new Hashtable<String,Object>();

    static Hashtable<String,Object> injectedBeanTable = new Hashtable<String,Object>();

    public static void registBean(String beanName, Object beanInstance){
        initBeanTable.put(beanName,beanInstance);
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

    public static void registInjectBean(String bean,Object object){
        injectedBeanTable.put(bean,object);
    }

    public static Object getInjectBean(String beanName){
        return injectedBeanTable.get(beanName);
    }
}
