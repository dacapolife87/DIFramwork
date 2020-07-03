package org.hjjang.bean;

import org.hjjang.annotation.Bean;
import org.hjjang.annotation.Component;
import org.hjjang.bean.collection.BeanCollection;
import org.reflections.Reflections;

import java.util.Set;

public class BeanLoader {

    public void beanProcessor(){
        loader();
    }
    public void loader(){
        Reflections reflector = new Reflections("");

        Set<Class<?>> classSet = reflector.getTypesAnnotatedWith(Bean.class);

        System.out.println("BeanList!");
        for(Class clazz : classSet){
            System.out.println(clazz.getName());
            System.out.println(clazz.isAnnotation());
            BeanCollection.registBean(clazz);
        }
    }
}
